package com.addith.algorithms;

import com.addith.quantum.QuantumOperationCommand;

/**
 * The Class representing one Pipeline Step.
 * 
 * Design Pattern: Pipeline
 */
public class PipelineStep {

	/**
	 * Gets the step number.
	 *
	 * @return the step number
	 */
	public int getStepNumber() {
		return stepNumber;
	}

	/**
	 * Sets the step number.
	 *
	 * @param stepNumber the new step number
	 */
	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	/**
	 * Gets the step command.
	 *
	 * @return the step command
	 */
	public QuantumOperationCommand getStepCommand() {
		return stepCommand;
	}

	/**
	 * Sets the step command.
	 *
	 * @param stepCommand the new step command
	 */
	public void setStepCommand(QuantumOperationCommand stepCommand) {
		this.stepCommand = stepCommand;
	}

	/**
	 * Gets the step description.
	 *
	 * @return the step description
	 */
	public String getStepDescription() {
		return stepDescription;
	}

	/**
	 * Sets the step description.
	 *
	 * @param stepDescription the new step description
	 */
	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}

	/**
	 * Instantiates a new pipeline step.
	 *
	 * @param stepNumber      the step number
	 * @param stepCommand     the step command
	 * @param stepDescription the step description
	 */
	public PipelineStep(int stepNumber, QuantumOperationCommand stepCommand, String stepDescription) {
		super();
		this.stepNumber = stepNumber;
		this.stepCommand = stepCommand;
		this.stepDescription = stepDescription;
	}

	/** The step number. */
	int stepNumber;

	/** The step command. */
	QuantumOperationCommand stepCommand;

	/** The step description. */
	String stepDescription;

}
