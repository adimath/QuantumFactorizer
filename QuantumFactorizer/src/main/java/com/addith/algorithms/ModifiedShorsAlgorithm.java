/*
 * 
 */
package com.addith.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.addith.utils.ExceedsMaxQuibitsSupportedException;
import com.addith.utils.MathUtils;

/**
 * The modified Shor's Algorithm implementation
 * 
 * Shor’s factoring algorithm.
 * 
 * The overall quantum factoring algorithm is as follows:
 * 
 * 1. If N even, return the factor 2 2. Determine whether N = a^b for integers a
 * ≥ 1 and b ≥
 * 
 * 2: if yes, return the factor a
 * 
 * 3. Randomly choose a between 1 and N −1. If z = gcd(y,N) &gt; 1, return the
 * factor z.
 * 
 * 4. Use the order-finding algorithm to find the order r of y mod N, i.e., r
 * such that y^r mod N = 1.
 * 
 * 5. If r is even and y^(r/2)mod N != −1, then evaluate gcd(y^r/2 ±1,N). If one
 * of these is a non-trivial factor (i.e., other than 1), return that value as a
 * factor. If not, go back to step 3 and repeat.
 * 
 * The success rate of the last three steps must be reasonably high since this
 * is a probabilistic algorithm version (efficient for a classical simulation).
 * 
 *
 */
public class ModifiedShorsAlgorithm extends Algorithm {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ModifiedShorsAlgorithm.class.getName());

	/** The observers. */
	List<Observer> observers = new ArrayList<>();

	/** The Constant RANDOM. */
	static final Random RANDOM = new Random();

	// Todo Ideally the maxNumberOfRetries would be read from a parameter, or config
	// value which user can adjust; will plan to adjust in future, for
	// The max number of retries.
	// purposes of sample, a hard coded value should suffice
	private int maxNumberOfRetries = 30;

	/**
	 * Instantiates a new shor algorithm.
	 */
	public ModifiedShorsAlgorithm() {
		super();
	}

	/**
	 * Sets the observer for the shor algorithm. It responds to raise or activity
	 * raised by the shor logic
	 *
	 * @param observer the new observer
	 */
	public void setObserver(Observer observer) {
		observers.add(observer);
		this.addObserver(observer);
	}

	/**
	 * Execute Shor algorithm
	 *
	 * @param n the n
	 */
	public void execute(int n) {

		// Set Start time to track elapsed time
		long startTime = System.nanoTime();

		sendInitialDisplayMessage(String.format("Running the Shor's algorithm to factor %s  ", n));

		try {
			int factor = getFactor(n);

			long endTime = System.nanoTime();
			long timeElapsed = endTime - startTime;

			if (factor == -1) {
				// we failed
				sendDisplayMessage(String.format(
						"We are limited to 30 passes of the quantum portion, we failed to factor N=%s with that, we need more than\n\t\t 30 passes for this one or perhaps it is a prime number.",
						n));

				// run the prime algorithm
				sendDisplayMessage(
						String.format("Check if N=%s is a prime number, answer is : %s ", n, !MathUtils.isNotPrime(n)));

			} else if (factor == -2) {
				sendDisplayMessage(String.format("unsupported input ", n));
			} else {

				int otherFactor = getOtherFactor(n, factor);
				sendDisplayMessage(String.format("Result: The factors of n %s are %s and %s", n, factor, otherFactor));

			}

			sendDisplayMessage("Execution time in nanoseconds: " + timeElapsed);
			sendDisplayMessage("Execution time in milliseconds: " + timeElapsed / 1000000);
			sendDisplayMessage("Execution time in seconds: " + timeElapsed / 1000000000);
			sendDisplayMessage("Execution time in minutes: " + ((timeElapsed / 1000000000) / 60));

		} catch (ExceedsMaxQuibitsSupportedException qubitSizeException) {
			sendErrorMessage(qubitSizeException.getMessage());

			logger.error("Uknown error in Shor's algorithm: " + qubitSizeException);
			logger.error("Uknown error in Shor's algorithm: " + qubitSizeException.getStackTrace());

		} catch (Exception e) {
			sendErrorMessage(e.getMessage());

			logger.error("Uknown error in Shor's algorithm: " + e);
			logger.error("Uknown error in Shor's algorithm: " + e.getStackTrace());
			System.out.println("\n--------------- Location of log file: log/app.log -----------------------\n\n");
			System.exit(0);
		}

	}

	/**
	 * Gets the factor. The factor generation is done using multiple steps. The
	 * following logic works
	 * 
	 * -check for non-supported factorization inputs (e.g. less or upto 10)
	 * 
	 * -check for even number
	 * 
	 * -check for prime number up to 30 iterations (max capped)
	 * 
	 *
	 * @param n the n
	 * @return the factor
	 * @throws Exception                           throws the exception
	 * @throws ExceedsMaxQuibitsSupportedException throws the exceed exception
	 */
	public int getFactor(int n) throws Exception, ExceedsMaxQuibitsSupportedException {

		int numberOfTries = 0;

		// being supported
		if (n < 0) {
			sendDisplayMessage(String.format(
					"Result: Invalid case,  integers less than or equal to 0 are not supported for factorization.", n));
			return -2;
		}

		if (n == 1) {
			sendDisplayMessage(String.format(
					"Result: Invalid case, 1 only has one positive divisor (1 itself), so it is not supported for factorization.",
					n));
			return -2;
		}

		if (!MathUtils.isNotPrime(n) && n < 10) {
			sendDisplayMessage(String.format(
					"Result: Invalid case,  primes smaller than 10 are not supported for testing this simulation.", n));
			return -2;
		}

		// Step 1: check if input is an even number
		sendDisplayMessage(String.format("Step 1: check if input %s is an even number.", n));
		if (MathUtils.isEvenNumber(n)) {
			sendDisplayMessage("Result: An even number has been passed. One of the prime factors is 2.");
			return 2;
		}

		// Step 2: check if input is a prime power
		sendDisplayMessage(String.format("Step 2: check if input %s is a prime power.", n));
		int powerFactor = MathUtils.ispowerFactor(n);
		if (powerFactor > 1) {
			sendDisplayMessage(String.format(
					"Result: A prime power was passed. We did not need the quantum portion of the algorithm, factors include %s",
					powerFactor));
			return powerFactor;
		}

		// do- while; we dont exceed max retries or find factors,
		// whichever comes first
		do {

			numberOfTries++;

			// Step 3 : Pick a random base a, random number between 0 and limit,
			// hence we add 2, to avoid picking a=1 by accident, a=1 is not
			// useful
			int a = 2 + RANDOM.nextInt(n - 2);

			sendDisplayMessage(
					String.format("Step 3: Pass %s: Pick a random base a, we picked a = %s", numberOfTries, a));

			// Step 4 : Check if a is co-prime with number using the Greatest
			// common divisor (GCD) calculation
			sendDisplayMessage(String.format("Step 4: Pass %s: Check if a %s is co-prime with number %s using the "
					+ "Greatest%n\t\tcommon divisor(GCD) calculation. Two integers are "
					+ "said to be co-prime if the%n\t\tonly positive integer " + "that divides both of them is 1.",
					numberOfTries, a, n));
			//
			ExtendedEuclideanAlgorithm extendedEuclideanAlgorithmInstance = getExtendedEuclideanAlgorithmInstance();
			int gcd = extendedEuclideanAlgorithmInstance.getGcd(a, n);

			// Check if gcd > 1, if it is that means we already found a common
			// divisor
			if (gcd > 1) {
				sendDisplayMessage(String.format("Result: a = %s is not a co-prime with number = %s.%n\t "
						+ "Since they have a common divisor, we dont need to use the quantum part of the algorithm, "
						+ "one of the factors of n %s is %s", a, n, n, gcd));

				return gcd;
			}

			// Step 5: Use the order finding algo to calculate order
			// between a and n
			sendDisplayMessage(String.format(
					"Step 5: Pass %s: a = %s is a co-prime with number = %s. \n\t\tUse the quantum order finding algorithm.",
					numberOfTries, a, n));
			int order = getOrderFindingAlgorithmInstance().execute(a, n);

			// Step 6 : check if order % 2 is 0, that is if its even
			sendDisplayMessage(String.format(
					"Step 6: Pass %s: Check if the order %s is even. Odd values of order/period are not relevant to\n\t\t factorization.",
					numberOfTries, order));

			if (order % 2 != 0) {
				// order is odd number, hence not relevant to factorization, we
				// must calculate order again
				sendDisplayMessage(String.format(
						"\t\tOrder is odd, we must repeat the quantum order finding algorithm to find another suitable order",
						numberOfTries, order, n));
				continue;
			}

			// order is even
			// check if a ^ r/2 mod N != −1
			sendDisplayMessage(String.format(
					"Step 7: Pass %s: Check if the order r %s is such that a ^ r/2 mod N != −1 . Order values such that\n\t\t"
							+ "a ^ r/2 mod N == −1 are not relevant to factorization",
					numberOfTries, order));

			int value = ((int) Math.pow(a, (order / 2)) % n);
			if (value == -1) {
				sendDisplayMessage(String.format(
						"\t\tOrder r is such that a ^ r/2 mod N == −1, we must repeat the quantum order finding\n\t\t algorithm to find another suitable order",
						numberOfTries, order, n));

				continue;

			}

			// evaluate gcd(y^r/2 -1,N). If one of these is a
			// non-trivial factor (i.e., other than 1), or number itself
			// return that value as a factor.
			sendDisplayMessage(String.format(
					"Step 8: Pass %s: Evaluate gcd(a^r/2 -1,N). If one of these is a non-trivial factor \n\t\t(i.e., "
							+ "other than 1 or number itself) return that value as a factor)",
					numberOfTries));

			int pCase1 = ((int) Math.pow(a, (order / 2)) - 1);

			int gcdCase1 = extendedEuclideanAlgorithmInstance.getGcd(pCase1, n);

			if (gcdCase1 > 1 && gcdCase1 != n) {
				sendDisplayMessage(String.format(
						"\t\tgcd(a^r/2 - 1,N) evaluated to %s, that must be one of the factors of N", gcdCase1, n));

				return gcdCase1;
			}

			// evaluate gcd(y^r/2 +1,N). If one of these is a
			// non-trivial factor (i.e., other than 1), or number itself
			// return that value as a factor.
			sendDisplayMessage(String.format("Step 9: Pass %s: Evaluate gcd(a^r/2 + 1,N). If one of these is a "
					+ "non-trivial factor \n\t\t(i.e., other than 1 or number itself) "
					+ "return that value as a factor)", numberOfTries));

			int pCase2 = ((int) Math.pow(a, (order / 2)) + 1);

			int gcdCase2 = extendedEuclideanAlgorithmInstance.getGcd(pCase2, n);

			if (gcdCase2 > 1 && gcdCase2 != n) {
				sendDisplayMessage(String.format(
						"\t\tgcd(a^r/2 + 1,N) evaluated to %s, that must be one of the factors of N ", gcdCase2, n));
				return gcdCase2;
			}

			// If number of tries haven't exceeded max allowed, repeat
			if (numberOfTries < maxNumberOfRetries) {
				sendDisplayMessage(String.format(
						"\t\tWe ran all steps but couldnt find the factors, we must repeat the quantum order finding algorithm\n\t\t to find another suitable order",
						numberOfTries, order, n));
			}

		} while (numberOfTries < maxNumberOfRetries);

		// return -1 when we fail to factor within max number of retries a839llowed
		return -1;
	}

	private int getOtherFactor(int number, int oneofTheFactors) {
		return number / oneofTheFactors;
	}

	/**
	 * Gets the order finding algorithm instance.
	 *
	 * @return the order finding algorithm instance
	 */
	public OrderFindingAlgorithm getOrderFindingAlgorithmInstance() {
		// Lazy loading design pattern
		// We are doing lazy instantiation on purpose ; in many cases we dont
		// need to run the quantum portion

		OrderFindingAlgorithm orderFindingAlgorithmInstance = new OrderFindingAlgorithm();
		for (Observer o : observers) {
			orderFindingAlgorithmInstance.addObserver(o);
		}
		return orderFindingAlgorithmInstance;
	}

	/**
	 * Gets the extended Euclidean algorithm instance. Extended Euclidean algorithm
	 * for computing the polynomial greatest common divisor and the coefficients.
	 * The extended Euclidean algorithm is particularly useful when a and b are
	 * coprime.
	 * 
	 * @return the extended Euclidean algorithm instance
	 */
	public ExtendedEuclideanAlgorithm getExtendedEuclideanAlgorithmInstance() {
		// Lazy loading design pattern
		// We are doing lazy instantiation on purpose ; in many cases we don't
		// need to run the euclidean portion

		ExtendedEuclideanAlgorithm extendedEuclideanAlgorithmInstance = new ExtendedEuclideanAlgorithm();
		for (Observer o : observers) {
			extendedEuclideanAlgorithmInstance.addObserver(o);
		}
		return extendedEuclideanAlgorithmInstance;
	}
}