/**
 * 
 */
package com.addith.quantum;

/**
 * 
 * "ConcreteCommand" implementation for ModularExponentiation quantum operation
 * 
 * ModularExponentiation quantum operation command implements Apply by invoking
 * the corresponding operation(s) on the Register object (Receiver). It defines
 * a binding between a Receiver object (Register object) and an action (apply
 * ModularExponentiation). This allows for loose coupling between the register
 * object and the calling algorithm class. Register object operations can be
 * changed without having to change the algorithm classes.
 * 
 * Design pattern: Command
 * 
 */
public class ModularExponentiationQuantumOperation implements QuantumOperationCommand {

	/** stores the Receiver instance of the ConcreteCommand */
	private QuantumRegisterInterface sourceQuantumReceiverObject;

	private QuantumRegister targetQuantumReceiverObject;
	int a;
	int n;

	/**
	 * Instantiates a new Modular Exponentiation quantum operation.
	 * 
	 *
	 * @param sourceQuantumReceiver the source quantum receiver
	 * @param targetQuantumReceiver the target quantum receiver
	 * @param a                     the a
	 * @param n                     the n
	 */
	public ModularExponentiationQuantumOperation(QuantumRegisterInterface sourceQuantumReceiver,
			QuantumRegister targetQuantumReceiver, int a, int n) {
		super();

		// Todo Caveat, this command takes different number of inputs than the otherand
		// is , need to rethink if it can be handled betterpattern could be messed up,
		// if you wire the steps incorrectly, need to consider that when refactoring

		this.sourceQuantumReceiverObject = sourceQuantumReceiver;

		this.targetQuantumReceiverObject = targetQuantumReceiver;

		this.a = a;

		this.n = n;

	}

	/**
	 * This method executes the command by invoking the corresponding method of the
	 * Receiver instance.
	 */
	@Override
	public void apply() {

		sourceQuantumReceiverObject.performModularExponentiation(targetQuantumReceiverObject, a, n);

	}

}
