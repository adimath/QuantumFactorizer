package com.addith.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.CompletableFuture;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.addith.algorithms.ModifiedShorsAlgorithm;
import com.addith.utils.MessageCategory;

/**
 * "Concrete gui view Strategy" implementation; part of the Strategy Design
 * Pattern
 * <ul>
 * <li>implements the view strategy using the Strategy interface.</li>
 * </ul>
 * 
 * Design Pattern: Strategy, Observer
 * 
 * 
 */
public class GuiStrategy implements ViewStrategy, ActionListener {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(GuiStrategy.class.getName());

	/** The window GUI instance. */
	GuiWindow window;

	/** The shor algorithms instance. */
	protected ModifiedShorsAlgorithm shorInstance;

	/**
	 * Default constructor
	 */
	public GuiStrategy() {
		super();
	}

	/**
	 * This method implements the algorithm operation defined by the Strategy
	 * interface.
	 */
	@Override
	public void launchView() {

		GuiStrategy myViewStrategyRef = this;

		// Get an instance of Shor's algorithm and
		// add the View strategy selected as observer
		shorInstance = new ModifiedShorsAlgorithm();
		shorInstance.setObserver(this);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					window = new GuiWindow(myViewStrategyRef);
					window.formQuantumPrimeFactors.setVisible(true);
				} catch (Exception e) {
					logger.error("$Runnable.run()", e); //$NON-NLS-1$
				}
			}
		});
	}

	/**
	 * Updating the end user with different types of messages
	 * 
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {

		HashMap<MessageCategory, String> received = (HashMap<MessageCategory, String>) arg;

		if (received != null) {
			if (received.containsKey(MessageCategory.INFORMATION)) {

				displayMessage(received.get(MessageCategory.INFORMATION));

			} else if (received.containsKey(MessageCategory.WELCOME)) {

				displayWelcome(received.get(MessageCategory.WELCOME));

			} else {

				displayError(received.get(MessageCategory.ERROR));
			}
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

		JTextArea outputTextArea = (JTextArea) window.getComponentByName("outputTextArea");
		outputTextArea.setForeground(Color.BLACK);
		outputTextArea.append(displayString);
		outputTextArea.append("\n");

		outputTextArea.setCaretPosition(outputTextArea.getText().length());

		logger.info(displayString);

	}

	/**
	 * Any error that occurs during the execution * *
	 * 
	 * @see com.addith.view.ViewStrategy#displayError(java.lang.String)
	 */
	@Override
	public void displayError(String displayError) {

		JTextArea outputTextArea = (JTextArea) window.getComponentByName("outputTextArea");
		outputTextArea.setForeground(Color.RED);
		outputTextArea.append(displayError);
		outputTextArea.append("\n");
		outputTextArea.setCaretPosition(outputTextArea.getText().length());
		// and throw error box may be future

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

		JTextArea outputTextArea = (JTextArea) window.getComponentByName("outputTextArea");
		outputTextArea.setText("");
		outputTextArea.setForeground(Color.green);
		outputTextArea.append(displayWelcome);
		outputTextArea.append("\n");
		outputTextArea.setCaretPosition(outputTextArea.getText().length());

		logger.info(displayWelcome);
	}

	/**
	 * Parses the argument. accept user input and try to parse accepted user input
	 * to int, if unsuccessful, display error
	 * 
	 *
	 * @param arg the arg
	 * @return the int
	 */
	private int parseArgmument(String arg) {
		try {
			return Integer.parseInt(arg.trim());
		} catch (NumberFormatException e) {

			logger.error("parseArgmument(String)", e); //$NON-NLS-1$

			// displayError()
			displayError("Argument must be an integer, instead we got: " + arg);

			// log this instead of showing to the user
			logger.error("Argument must be an integer, instead we got: " + arg);
			logger.error("This caused the error below");
			logger.error(e.getMessage());

			return -1;
		}
	}

	/*
	 * A threadable version to update the output text area in real-time
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		try {

			JTextField mytextField = (JTextField) window.getComponentByName("inputNumberField");

			int numberToBeFactored = parseArgmument(mytextField.getText());

			if (numberToBeFactored != -1) {

				mytextField.setEnabled(false);

				JButton submitButton = (JButton) window.getComponentByName("calculateButton");
				submitButton.setEnabled(false);

				// We are calling the algorithm execute as async so that it
				// doesnt block our UI updates delivered by the observer pattern
				// method call or code to be asynch.
				CompletableFuture.runAsync(() -> {

					// Launch Shors Algorithm
					shorInstance.execute(numberToBeFactored);
					mytextField.setText("");
					mytextField.setEnabled(true);

				});
			}
		} catch (Exception e1) {
			logger.error("actionPerformed(ActionEvent)", e1); //$NON-NLS-1$
		}
	}
}