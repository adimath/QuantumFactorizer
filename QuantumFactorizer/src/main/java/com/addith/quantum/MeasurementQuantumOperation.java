/**
 * 
 */
package com.addith.quantum;

/**
 * 
 * "ConcreteCommand" implementation for Measurement quantum operation
 * 
 * Measurement quantum operation command implements apply by invoking the
 * corresponding operation(s) on the Register object (Receiver). It defines a
 * binding between a Receiver object (Register object) and an action (register
 * measure). This allows for loose coupling between the register object and the
 * calling algorithm class. Register object operations can be changed without
 * having to change the algorithm classes.
 * 
 * Design pattern: Command
 * 
 */
public class MeasurementQuantumOperation implements QuantumOperationCommand {

	/** stores the Receiver instance of the ConcreteCommand */
	private QuantumRegisterInterface quantumRegisterReceiverObject;

	/**
	 * Instantiates a new Measurement quantum operation
	 * 
	 * Constructor.
	 *
	 * @param quantumRegister the quantum register
	 */
	public MeasurementQuantumOperation(QuantumRegisterInterface quantumRegister) {
		super();
		this.quantumRegisterReceiverObject = quantumRegister;
	}

	/**
	 * This method executes the command by invoking the corresponding method of the
	 * Receiver instance.
	 */
	@Override
	public void apply() {

		quantumRegisterReceiverObject.measure();

	}
}