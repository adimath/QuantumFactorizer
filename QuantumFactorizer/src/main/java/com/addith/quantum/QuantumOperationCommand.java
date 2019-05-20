/**
 * 
 */
package com.addith.quantum;

/**
 * 
 * Command interface for Quantum operation
 * 
 * Quantum operation command declares an interface for executing a quantum
 * operation (command interface ).
 * 
 * Design pattern: Command
 * 
 */
public interface QuantumOperationCommand {

	/**
	 * This abstract method must be implemented by the ConcreteCommand
	 * implementation.
	 */
	public void apply(); // common execute method of the command interface

}
