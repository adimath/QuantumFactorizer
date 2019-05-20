/**
 * 
 */
package com.addith.quantum;

/**
 * 
 * "ConcreteCommand" implementation for Quantum Fourier Transform quantum
 * operation
 * 
 * Quantum Fourier Transform quantum operation command implements Apply by
 * invoking the corresponding operation(s) on the Register object (Receiver). It
 * defines a binding between a Receiver object (Register object) and an action
 * (apply quantumFourierTransform). This allows for loose coupling between the
 * register object and the calling algorithm class. Register object operations
 * can be changed without having to change the algorithm classes.
 * 
 * Design pattern: Command
 * 
 */
public class QuantumFourierTransformQuantumOperation implements QuantumOperationCommand {

	/**
	 * Instantiates a new quantum fourier transform quantum operation.
	 *
	 * @param quantumRegister the quantum register
	 */
	public QuantumFourierTransformQuantumOperation(QuantumRegisterInterface quantumRegister) {
		super();
		this.quantumRegisterReceiverObject = quantumRegister;
	}

	/** stores the Receiver instance of the ConcreteCommand */
	private QuantumRegisterInterface quantumRegisterReceiverObject;

	/**
	 * This method executes the command by invoking the corresponding method of the
	 * Receiver instance.
	 */
	@Override
	public void apply() {
		quantumRegisterReceiverObject.quantumFourierTransform();

		// quantumRegisterReceiverObject.qft();

	}

}
