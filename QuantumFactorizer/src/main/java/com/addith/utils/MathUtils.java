package com.addith.utils;

import java.math.BigInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class provides math and linear algebra theorems and functions.
 */
public class MathUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(MathUtils.class.getName());

	// Suppresses default constructor, ensuring non-instantiability.
	private MathUtils() {
	}

	/**
	 * check the number if a power factor of other number
	 *
	 * @param n the n
	 * @return the int
	 */
	public static int ispowerFactor(int n) {
		double log2N = Math.log(n) / Math.log(2);
		// Try values of root_y(n) starting at log2n and working your way down
		// to 2.
		double logFloor = Math.floor(log2N);
		if (Math.abs(log2N - logFloor) < 0.000005) {
			return 2;
		}

		logFloor--;
		for (; logFloor > 1; logFloor--) {
			double pow = Math.pow(n, (1 / logFloor));
			if (isAlmostInt(pow)) {
				return (int) Math.round(pow);
			}
		}
		return 0;
	}

	/**
	 * Checks if the input number is int or not
	 *
	 * @param x the x
	 * @return true, if is almost int
	 */
	public static boolean isAlmostInt(double x) {
		return Math.abs(x - Math.round(x)) < 0.0000001;
	}

	/**
	 * Generating the mod of an input number
	 *
	 * @param n the n
	 * @param m the m
	 * @return the int
	 */
	public static int mod(int n, int m) {
		if (m == 0) {
			return n;
		}
		byte mPositive = 1;
		if (m < 0) {
			mPositive = -1;
			m = -m;
			n = -n;
		}
		return (n >= 0) ? n % m * mPositive : (m + n % m) % m * mPositive;
	}

	/**
	 * Checks if input is even number.
	 *
	 * @param n the n
	 * @return true, if is even number
	 */
	public static boolean isEvenNumber(int n) {
		return n % 2 == 0;
	}

	/**
	 * Checks if input is not prime number.
	 *
	 * @param n the n
	 * @return true, if is not prime
	 */
	public static boolean isNotPrime(int n) {

		BigInteger possiblePrime = BigInteger.valueOf(n);

		// we are passing 1 as certainty, as we want to know for sure that its
		// a prime
		return !possiblePrime.isProbablePrime(1);
	}

	/**
	 * Continued fraction expansion. A continued fraction is the representation of a
	 * real number by a sequence (possible infinite) of integers:
	 * 
	 * [x0, x1, x2....xn} = x0 +( 1/ x1+ (1/x2 + (1/ .......1/xn))) = p/q
	 *
	 * @param x the x
	 * @param a the a
	 * @param n the n
	 * @return the int
	 */
	public static int continuedFraction(double x, int a, int n) {

		try {
			int q0 = 1;
			int p0 = (int) (x);

			double x0 = x - p0;
			int x1 = (int) (1.0 / x0);
			int q1 = x1;

			int qnMinus1 = q1;
			int qnMinus2 = q0;

			int an = x1;
			int qn = q1;
			double xn = (1.0 / x0) - x1;

			int qTemp = 1;
			while (xn != 0) {
				// Iterating through values q to find if they are the period
				an = (int) (1.0 / xn);
				qTemp = qn;
				qn = an * qnMinus1 + qnMinus2;

				qnMinus2 = qnMinus1;
				qnMinus1 = qTemp;

				xn = (1.0 / xn) - an;

				if (xn < Double.MIN_VALUE) {
					xn = 0;
				}
				// note that The Biginteger modPow method will only accept a negative exponent
				// -qn if a and n are coprime. we
				// have already verified they are coprimes before getting here
				if (BigInteger.valueOf(a).modPow(BigInteger.valueOf(qn), BigInteger.valueOf(n)).longValue() == 1) {
					xn = 1;
					break;
				}
				if (xn == 0) {

					return 0;

				}

			}
			return qn;
		} catch (Exception e) {
			logger.error("continuedFraction(double, int, int) ", e); //$NON-NLS-1$
			throw e;
		}
	}
}