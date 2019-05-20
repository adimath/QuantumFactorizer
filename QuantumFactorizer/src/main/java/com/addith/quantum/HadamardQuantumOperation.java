/**
 * 
 */
package com.addith.quantum;

/**
 * 
 * "ConcreteCommand" implementation for Hadamard quantum operation
 * 
 * Hadamard quantum operation command implements Apply by invoking the
 * corresponding operation(s) on the Register object (Receiver). It defines a
 * binding between a Receiver object (Register object) and an action (apply
 * hadamard). This allows for loose coupling between the register object and the
 * calling algorithm class. Register object operations can be changed without
 * having to change the algorithm classes.
 * 
 * Design pattern: Command
 * 
 */
public class HadamardQuantumOperation implements QuantumOperationCommand {

	/**
	 * Instantiates a new hadamard quantum operation.
	 *
	 * @param quantumRegister the quantum register
	 */
	public HadamardQuantumOperation(QuantumRegisterInterface quantumRegister) {
		super();
		this.quantumRegisterReceiverObject = quantumRegister;
	}

	/** stores the Receiver instance(Register object) of the ConcreteCommand. */
	private QuantumRegisterInterface quantumRegisterReceiverObject;

	/**
	 * This method executes the command by invoking the corresponding method of the
	 * Receiver instance. For Hadmard operation, we invoke corresponding method on
	 * the Register object
	 */
	public void apply() {
		quantumRegisterReceiverObject.applyHadamard();

	}

}
