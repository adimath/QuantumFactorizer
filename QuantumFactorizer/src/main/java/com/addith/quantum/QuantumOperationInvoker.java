/**
 * 
 */
package com.addith.quantum;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * "Command Invoker" implementation for Quantum operation invoker
 * 
 * Quantum operation invoker asks the command to carry out the request. When
 * ExecuteOperation is called, it subsequently calls the apply method on the
 * command instance.
 * 
 * 
 * Design pattern: Command
 * 
 */
public class QuantumOperationInvoker {

	/**
	 * stores the QuantumOperationCommand instance of the QuantumOperationsInvoker.
	 */
	private QuantumOperationCommand qCommand;

	/** The quantum operations. */
	private final List<QuantumOperationCommand> quantumOperations = new ArrayList<>();

	/**
	 * Default constructor.
	 */
	public QuantumOperationInvoker() {
		super();
	}

	/**
	 * Constructor.
	 *
	 * @param cmd the cmd
	 */
	public QuantumOperationInvoker(QuantumOperationCommand cmd) {
		super();
		qCommand = cmd;
	}

	/**
	 * This method stores a ConcreteCommand instance.
	 *
	 * @param cmd the cmd
	 */
	public void storeCommand(QuantumOperationCommand cmd) {
		qCommand = cmd;
	}

	/**
	 * This method performs the actions associated with the ConcreteCommand
	 * instance.
	 */
	public void executeOperation() {

		quantumOperations.add(qCommand);
		qCommand.apply();
	}

	/**
	 * Execute operation.
	 *
	 * @param quantumOperationCommand the quantum operation command
	 */
	public void executeOperation(QuantumOperationCommand quantumOperationCommand) {
		quantumOperations.add(quantumOperationCommand);
		quantumOperationCommand.apply();
	}

}
