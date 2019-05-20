package com.addith;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.addith.view.ConsoleStrategy;
import com.addith.view.GuiStrategy;
import com.addith.view.ViewContext;

/**
 * The Class PrimeFactorsCalculatorMain. This is the application launcher.
 * 
 * The purpose of this application is to simulate modified Shor's algorithm
 * including the simulation of quantum order finding portion to calculate prime
 * factors of a semi prime.
 * 
 * A semiprime is a natural number that is the product of two prime numbers.
 * Semiprimes are highly useful for cryptography, especially public key
 * cryptography like RSA. It relies on the fact that finding two large primes
 * and multiplying them together (resulting in a semiprime) is computationally
 * simple whereas finding the original factors appears to be difficult. Shor's
 * algorithm, is a quantum algorithm for integer factorization,formulated in
 * 1994. It solves the following problem: Given an integer, find its prime
 * factors. On a quantum computer/simulation, to factor an integer, Shor's
 * algorithm runs in polynomial time. Lets use modified Shors Algorithm with
 * Quantum circuit simulation to calculate the factors of a semiprime.
 * 
 * The application supports factoring of semi primes from 15 to 600. As expected
 * from a simulation in classical computers, the larger numbers are much slower.
 * Feel free to input all kinds of numbers to test the simulation limits.
 * 
 * The number 56153 (which is the largest claimed number factored via a compiled
 * minimization algorithm) will throw an out of memory exception, we wont have
 * resources for enough qubits :)
 */
@SpringBootApplication
public class PrimeFactorsCalculatorMain {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(PrimeFactorsCalculatorMain.class.getName());

	/** The io context. */
	private static ViewContext ioContext;

	/**
	 * Gets the io context.
	 *
	 * @return the io context
	 */
	public static ViewContext getIoContext() {
		return ioContext;
	}

	/**
	 * The main method.
	 *
	 * @param args supports one argument, ui If no params passed, we use the command
	 *             line output. If Ui is passed as a param, we launch the swing gui.
	 * 
	 */
	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(PrimeFactorsCalculatorMain.class);
		app.setAdditionalProfiles("Production");
		app.setBannerMode(Banner.Mode.OFF);
		// false to support headless mode.
		app.setHeadless(false);
		System.out.println("\n\n===========================================================================");
		System.out.println("\n================= Quantum Factorizer (Modified Shor's algorithm) ====================");
		System.out.println("\n===========================================================================");
		System.out.println("\n--------------- Location of log file: log/app.log -----------------------\n\n");

		app.run(args);
		// Spring boot sets java.awt.headless to true by default. Setting it to

		ioContext = null;

		// We are using the strategy pattern to decide how the display/IO would
		// work for the program
		// Ideally we would also keep this in a config file, but for the
		// purposes of sample, we are more focused on showing that the strategy pattern
		// helps us pick different kinds of ui for same backend at runtime. More so, we
		// can
		// easily add web and mobile view strategies

		// arguments validation

		// check if any parameters were passed
		if (args.length == 0) {

			// If no params passed, use the console display strategy
			ioContext = new ViewContext(new ConsoleStrategy());

		} else if (args.length == 1 && !args[0].isEmpty() && args[0].equalsIgnoreCase("ui")) {

			// If the param passed is UI, use the gui display strategy
			ioContext = new ViewContext(new GuiStrategy());

		}

		else {
			System.err.println(
					"Incorrect parameters passed. Program can be invoked with only 0 or 1 parameter.If providing a param, the only supported param value is UI or ui");
			logger.fatal(
					"Incorrect parameters passed. Program can be invoked with only 0 or 1 parameter.If providing a param, the only supported param value is UI or ui");
			System.out.println("\n--------------- Location of log file: log/app.log -----------------------\n\n");
			System.exit(1);
		}
		// Launch selected view strategy
		ioContext.invokeContextStrategy();
	}
}