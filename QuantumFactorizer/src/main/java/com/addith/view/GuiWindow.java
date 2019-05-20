package com.addith.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class GuiWindow.
 * 
 * The main GUI class for rendering the prime factorizer form
 * 
 */
public class GuiWindow {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(GuiWindow.class.getName());

	/** The jframe form quantum prime factors. */
	JFrame formQuantumPrimeFactors;

	/** The component map to store the elements of the form. */
	private HashMap<String, Component> componentMap = new HashMap<String, Component>();

	/**
	 * Create the main window.
	 *
	 * @param viewStrategyRef the view strategy ref
	 */
	public GuiWindow(GuiStrategy viewStrategyRef) {
		initialize(viewStrategyRef);
		createComponentMap();
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 * @param viewStrategyRef the view strategy ref
	 */
	private void initialize(GuiStrategy viewStrategyRef) {

		int fontSize = getFontSize();

		formQuantumPrimeFactors = new JFrame();
		formQuantumPrimeFactors.setResizable(false);
		formQuantumPrimeFactors.setTitle("Quantum Prime Factors Calculator");
		formQuantumPrimeFactors.setBounds(100, 100, 1750, 950);
		formQuantumPrimeFactors.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 31, 522, 141, 206, 0 };
		gridBagLayout.rowHeights = new int[] { 109, 22, 32, 24, 425, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		formQuantumPrimeFactors.getContentPane().setLayout(gridBagLayout);

		JLabel lblSemiPrimeDesc = new JLabel(
				"<html><BR />A semiprime is a natural number that is the product of two prime numbers. Semiprimes are highly useful for cryptography,"
						+ " especially public key cryptography like RSA. It relies on the fact that finding two large primes and multiplying them together (resulting in a semiprime) is"
						+ " computationally simple, whereas finding the original factors appears to be difficult.<br /><br/><B>Shor's algorithm</B>, is a quantum algorithm for integer factorization, "
						+ "formulated in 1994. It solves the following problem: Given an integer, find its prime factors. On a quantum computer/simulation, to factor an integer, Shor's "
						+ "algorithm runs in polynomial time.<br/>Let's use modified Shors Algorithm with Quantum circuit simulation to calculate the factors of a semiprime.<BR /><BR /></html>");
		GridBagConstraints gbc_lblSemiPrimeDesc = new GridBagConstraints();
		gbc_lblSemiPrimeDesc.fill = GridBagConstraints.BOTH;
		gbc_lblSemiPrimeDesc.insets = new Insets(0, 0, 5, 0);
		gbc_lblSemiPrimeDesc.gridwidth = 3;
		gbc_lblSemiPrimeDesc.gridx = 1;
		gbc_lblSemiPrimeDesc.gridy = 0;
		formQuantumPrimeFactors.getContentPane().add(lblSemiPrimeDesc, gbc_lblSemiPrimeDesc);
		logger.info("FontSize: " + Toolkit.getDefaultToolkit().getScreenSize().getWidth() + ":" + fontSize);
		lblSemiPrimeDesc.setFont(new Font("Arial", Font.PLAIN, fontSize));

		JButton btnCalculate = new JButton("Calculate Prime Factors");
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		btnCalculate.setName("calculateButton");
		btnCalculate.addActionListener(viewStrategyRef);
		btnCalculate.setEnabled(false);

		GridBagConstraints gbc_btnCalculate = new GridBagConstraints();
		gbc_btnCalculate.fill = GridBagConstraints.BOTH;
		gbc_btnCalculate.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalculate.gridx = 2;
		gbc_btnCalculate.gridy = 2;
		formQuantumPrimeFactors.getContentPane().add(btnCalculate, gbc_btnCalculate);

		JTextField inputNumberTextField = new JTextField("");
		inputNumberTextField.setColumns(10);
		inputNumberTextField.setName("inputNumberField");
		inputNumberTextField.setFont(new Font("Arial", Font.PLAIN, fontSize));

		// listening to enter key for pressing the button
		inputNumberTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {

				if (inputNumberTextField.getText().trim().length() > 0) {
					btnCalculate.setEnabled(true);
				}

				if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
					btnCalculate.doClick();
				}
			}
		});

		JLabel lblGetNumberInput = new JLabel(
				"Enter any positive semiprime number (product of two prime numbers) between 15 and 999    ");
		lblGetNumberInput.setFont(new Font("Tahoma", Font.BOLD, fontSize));
		GridBagConstraints gbc_lblGetNumberInput = new GridBagConstraints();
		gbc_lblGetNumberInput.anchor = GridBagConstraints.WEST;
		gbc_lblGetNumberInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblGetNumberInput.gridx = 1;
		gbc_lblGetNumberInput.gridy = 1;
		formQuantumPrimeFactors.getContentPane().add(lblGetNumberInput, gbc_lblGetNumberInput);
		GridBagConstraints gbc_inputNumberTextField = new GridBagConstraints();
		gbc_inputNumberTextField.fill = GridBagConstraints.BOTH;
		gbc_inputNumberTextField.insets = new Insets(0, 0, 5, 5);
		gbc_inputNumberTextField.gridx = 2;
		gbc_inputNumberTextField.gridy = 1;
		formQuantumPrimeFactors.getContentPane().add(inputNumberTextField, gbc_inputNumberTextField);

		JLabel lblNewLabel = new JLabel(
				"(feel free to enter primes, non semiprimes or larger numbers to test the simulation limits!)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		formQuantumPrimeFactors.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblOutput = new JLabel("<html><BR />Output:</html>");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		lblOutput.setName("outputAreaLabel");
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.anchor = GridBagConstraints.WEST;
		gbc_lblOutput.fill = GridBagConstraints.VERTICAL;
		gbc_lblOutput.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutput.gridx = 1;
		gbc_lblOutput.gridy = 3;
		formQuantumPrimeFactors.getContentPane().add(lblOutput, gbc_lblOutput);

		JTextArea textArea = new JTextArea(15, 50);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
		textArea.setBackground(Color.WHITE);
		textArea.setName("outputTextArea");
		textArea.setBounds(29, 215, 771, 576);
		textArea.setEditable(false);
		textArea.setText("");
		componentMap.put("outputTextArea", textArea);

		JScrollPane scroll = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setViewportBorder(UIManager.getBorder("TextArea.border"));
		scroll.setOpaque(false);
		scroll.setName("outputAreaScroll");
		GridBagConstraints gbc_scroll = new GridBagConstraints();
		gbc_scroll.fill = GridBagConstraints.BOTH;
		gbc_scroll.gridwidth = 3;
		gbc_scroll.gridx = 1;
		gbc_scroll.gridy = 4;
		formQuantumPrimeFactors.getContentPane().add(scroll, gbc_scroll);
		formQuantumPrimeFactors.setLocationRelativeTo(null);
	}

	/**
	 * Gets the font size based on the screen resolution.
	 *
	 * @return the font size
	 */
	private int getFontSize() {

		// default font size
		int fontSize = 16;

		try {

			// get the screen resolution
			int screenResolution = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

			logger.info("Screen Resolution:" + screenResolution);

			switch (screenResolution) {

			case 3840:
				fontSize = 25;
				break;

			case 2560:
				fontSize = 23;
				break;

			case 2048:
				fontSize = 21;
				break;

			case 1920:
				fontSize = 20;
				break;

			case 1856:
				fontSize = 19;
				break;

			case 1792:
				fontSize = 19;
				break;

			case 1680:
				fontSize = 18;
				break;

			default:
				fontSize = 16;
				logger.error("Screen resolution not detected. Using default font size");
				break;
			}
		} catch (HeadlessException e) {
			logger.error("getFontSize(). Unable to determine screen resolution", e); //$NON-NLS-1$
		}
		return fontSize;
	}

	/**
	 * Creates the component map.
	 */
	private void createComponentMap() {
		Component[] components = formQuantumPrimeFactors.getContentPane().getComponents();
		for (int i = 0; i < components.length; i++) {
			try {
				componentMap.put(components[i].getName(), components[i]);
			} catch (Exception e) {
				logger.warn("createComponentMap() - exception ignored", e); //$NON-NLS-1$
			}
		}
	}

	/**
	 * Gets the component by name.
	 *
	 * @param name the name
	 * @return the component by name
	 */
	public Component getComponentByName(String name) {
		if (componentMap.containsKey(name)) {
			return componentMap.get(name);
		} else {
			return null;
		}
	}
}