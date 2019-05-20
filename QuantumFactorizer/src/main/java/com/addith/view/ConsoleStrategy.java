/**
 * 
 */
package com.addith.view;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.addith.algorithms.ModifiedShorsAlgorithm;
import com.addith.utils.MessageCategory;

/**
 * "Concrete console view Strategy" implementation; part of the Strategy Design
 * Pattern
 * <ul>
 * <li>implements the view strategy for command-line using the Strategy
 * interface.</li>
 * </ul>
 * 
 * 
 */
public class ConsoleStrategy implements ViewStrategy {

	/** Logger for this class. */
	private static final Logger logger = LogManager.getLogger(ConsoleStrategy.class.getName());

	/**
	 * Default constructor.
	 */
	public ConsoleStrategy() {
		super();
	}

	/**
	 * This method implements the algorithm operation defined by the Strategy
	 * interface.
	 */
	@Override
	public void launchView() {

		// Display description message
		displayMessage(
				"\n\nA semiprime is a natural number that is the product of two prime numbers. Semiprimes are highly useful\n"
						+ "for cryptography, especially public key cryptography like RSA. It relies on the fact that finding \n"
						+ "two large primes and multiplying them together (resulting in a semiprime) is computationally simple,\n"
						+ "whereas finding the original factors appears to be difficult."
						+ "\n\nShor's algorithm, is a quantum algorithm for integer factorization,formulated in 1994. It solves the\n"
						+ "following problem: Given an integer, find its prime factors. On a quantum computer/simulation, to factor\n"
						+ "an integer, Shor's algorithm runs in polynomial time."
						+ "\n\nLets use modified Shors Algorithm with Quantum circuit simulation to calculate the factors\n"
						+ "of a semiprime.");

		// Create a Scanner object to accept user input.
		Scanner myObj = new Scanner(System.in);
		displayMessage("Enter any positive semiprime number (product of two prime numbers) between 15 and 999.\n"
				+ "(feel free to enter primes, non semiprimes or larger numbers to test the simulation limits!)\nEnter number :");

		// Accept user input
		String userInput = myObj.next();

		do {

			// parse user input and check its int
			int numberToBeFactored = parseArgmument(userInput);

			if (numberToBeFactored != -1) {

				// Get an instance of Shor's algorithm and
				// add the View strategy selected as observer
				ModifiedShorsAlgorithm shorInstance = new ModifiedShorsAlgorithm();
				shorInstance.setObserver(this);

				// Launch Shors Algorithm
				shorInstance.execute(numberToBeFactored);
			}

			displayMessage("\n\nEnter another positive semiprime number " + "(product of two prime numbers) to factor "
					+ "or press \"ctrl-c\"  to exit :");

			// Accept user input
			try {
				userInput = myObj.next();
			} catch (NoSuchElementException e) {
				System.err.println("Now exiting the Quantum Factorizer!");
				logger.error("Now exiting the Quantum Factorizer!");
				System.out.println("\n--------------- Location of log file: log/app.log -----------------------\n\n");
				System.exit(1);
			}
		} while (userInput.length() > 0);

		myObj.close();
	}

	/**
	 * accept user input and try to parse accepted user input to int, if
	 * unsuccessful Display error
	 *
	 * @param arg the arg
	 * @return the int
	 */
	protected int parseArgmument(String arg) {
		try {
			return Integer.parseInt(arg.trim());
		} catch (NumberFormatException e) {

			logger.error("Argument must be an integer, instead we got: " + arg);
			System.err.println("Argument must be an integer, instead we got: " + arg);
			// log this instead of showing to the user
			logger.error("This caused the error below");
			logger.error(e.getMessage());
			return -1;
		}
	}

	/**
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		HashMap<MessageCategory, String> received = (HashMap<MessageCategory, String>) arg1;

		if (received.containsKey(MessageCategory.INFORMATION)) {
			// Send the observed update to the console
			displayMessage(received.get(MessageCategory.INFORMATION));
		} else if (received.containsKey(MessageCategory.WELCOME)) {
			displayWelcome(received.get(MessageCategory.WELCOME));
		} else {
			displayError(received.get(MessageCategory.ERROR));
		}
	}

	/**
	 * General output message as the program executes to generate the numbers
	 * 
	 * 
	 * @see com.addith.view.ViewStrategy#displayMessage(java.lang.String)
	 */
	@Override
	public void displayMessage(String displayString) {
		System.out.println(displayString);
		System.out.println("\n");
		logger.info(displayString);
	}

	/**
	 * Any error that occurs during the execution * *
	 * 
	 * @see com.addith.view.ViewStrategy#displayError(java.lang.String)
	 */
	@Override
	public void displayError(String displayError) {

		displayError = "Error: " + displayError;
		System.err.println(displayError);
		logger.error(displayError);
	}

	/**
	 * Text area output logic. It begins with an empty non-editable text area
	 * 
	 * 
	 * * @see com.addith.view.ViewStrategy#displayWelcome(java.lang.String)
	 */
	@Override
	public void displayWelcome(String displayWelcome) {
		displayMessage(displayWelcome);
		logger.info(displayWelcome);
	}
}