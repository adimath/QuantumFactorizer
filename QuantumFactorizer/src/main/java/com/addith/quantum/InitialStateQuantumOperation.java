/**
 * 
 */
package com.addith.quantum;

/**
 * 
 * "ConcreteCommand" implementation for InitialState quantum operation
 * 
 * InitialState quantum operation command implements Apply by invoking the
 * corresponding operation(s) on the Register object (Receiver). It defines a
 * binding between a Receiver object (Register object) and an action (apply
 * initialstate). This allows for loose coupling between the register object and
 * the calling algorithm class. Register object operations can be changed
 * without having to change the algorithm classes.
 * 
 * Design pattern: Command
 * 
 */
public class InitialStateQuantumOperation implements QuantumOperationCommand {

	/** stores the Receiver instance of the ConcreteCommand */
	private QuantumRegisterInterface quantumRegisterReceiverObject;

	/**
	 * Instantiates a new InitialState quantum operation.
	 * 
	 * Constructor
	 *
	 * @param quantumRegister the quantum register
	 */
	public InitialStateQuantumOperation(QuantumRegisterInterface quantumRegister) {
		super();
		this.quantumRegisterReceiverObject = quantumRegister;
	}

	/**
	 * This method executes the command by invoking the corresponding method of the
	 * Receiver instance.
	 */
	@Override
	public void apply() {

		quantumRegisterReceiverObject.applyInitialState();
	}
}