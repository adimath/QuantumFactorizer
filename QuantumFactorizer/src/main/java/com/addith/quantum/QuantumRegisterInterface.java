package com.addith.quantum;

/**
 * 
 * 
 * The Interface QuantumRegisterInterface, represents the quantum register. The
 * interface helps mock the register using mockito framework for tests
 * 
 * Register interface is the "Receiver" interface for our command design
 * pattern. It knows how to perform the operations associated with the Quantum
 * Operations requested via the quantum operations implementing the Quantum
 * Operation Command.
 * 
 * Design pattern: Command
 * 
 */
public interface QuantumRegisterInterface {

	/**
	 * Gets the measured value.
	 *
	 * @return the measured value
	 */
	int getMeasuredValue();

	/**
	 * Gets the number of qubits.
	 *
	 * @return the number of qubits
	 */
	int getNumberOfQubits();

	/**
	 * Sets the number of qubits.
	 *
	 * @param numberOfQubits the new number of qubits
	 */
	void setNumberOfQubits(int numberOfQubits);

	/**
	 * Gets the states.
	 *
	 * @return the states
	 */
	ComplexState[] getStates();

	/**
	 * Sets the states.
	 *
	 * @param states the new states
	 */
	void setStates(ComplexState[] states);

	/**
	 * Gets the number of states stored.
	 *
	 * @return the number of states stored
	 */
	int getNumberOfStatesStored();

	/**
	 * Sets the number of states stored.
	 *
	 * @param numberOfStatesStored the new number of states stored
	 */
	void setNumberOfStatesStored(int numberOfStatesStored);

	/**
	 * Sets the measured value.
	 *
	 * @param measuredValue the new measured value
	 */
	void setMeasuredValue(int measuredValue);

	/**
	 * Apply initial state to this register.
	 */
	void applyInitialState();

	/**
	 * Transform register directly, apply hadamard operation to all Q states of this
	 * register, where Q = 2 ^ K and K is the numberOfQubits of the register.
	 */
	void applyHadamard();

	/**
	 * Measure the register and return the random value of a quantum measurement of
	 * this register. Measurement makes the register collapse randomly to one of the
	 * qubits i.e. after a "measurement" has been made, one state should equal 1,
	 * and the rest should be zero. To implement the random collapse, a random
	 * number between 0 and 1 is chosen and squared probabilities of the each qubits
	 * are subtracted from the random number till it becomes zero or negative.The
	 * qubit that causes this is the measured value.
	 * 
	 * @return the measured value
	 */
	int measure();

	/**
	 * Applies the modular exponentiation function to the contents of source
	 * register and store the result in target register.
	 *
	 * @param targetRegister the target register where the function values are
	 *                       stored, this will modify the target register
	 * @param a              the random base a
	 * @param n              the number being factored
	 * @return the target register after the function has been applied
	 */
	QuantumRegisterInterface performModularExponentiation(QuantumRegister targetRegister, int a, int n);

	/**
	 * Applies the quantum Fourier transform (QFT) to all states of this
	 * register.This is applied to the states itself and not what it holds.
	 */
	void quantumFourierTransform();

}