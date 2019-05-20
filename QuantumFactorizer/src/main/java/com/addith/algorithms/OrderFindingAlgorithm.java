/*
 * 
 */
package com.addith.algorithms;

import java.util.ArrayList;
import java.util.List;

import com.addith.quantum.HadamardQuantumOperation;
import com.addith.quantum.InitialStateQuantumOperation;
import com.addith.quantum.MeasurementQuantumOperation;
import com.addith.quantum.ModularExponentiationQuantumOperation;
import com.addith.quantum.QuantumFourierTransformQuantumOperation;
import com.addith.quantum.QuantumOperationInvoker;
import com.addith.quantum.QuantumRegister;
import com.addith.quantum.QuantumRegisterInterface;
import com.addith.utils.ExceedsMaxQuibitsSupportedException;
import com.addith.utils.MathUtils;

/**
 * The class that implements the Order finding algorithm. The order of the
 * modular exponential, referred to as the order of a mod N or ord(a), is the
 * smallest positive integer r such that a^r mod N = 1
 * 
 * Design Pattern: Command, Pipeline
 * 
 **/

public class OrderFindingAlgorithm extends Algorithm {

	public OrderFindingAlgorithm() {
		super();

	}

	/**
	 * Execute the order finding algorithm and calculate the order between base a
	 * and number n.
	 *
	 * @param a the base a
	 * @param n the number whose order need to be calculated
	 * @return the order of a mod n or ord(a), is the smallest positive integer r
	 *         such that a^r mod n = 1
	 * @throws ExceedsMaxQuibitsSupportedException the exceeds max qubits supported
	 *                                             exception
	 */
	public int execute(int a, int n) throws ExceedsMaxQuibitsSupportedException {

		// checking boundary conditions
		if (a <= 1 || n <= 1) {
			return 0;
		}

		// The order of the modular exponential, referred to as the order of a
		// mod N or ord(a), is the smallest positive integer r such that a^r mod
		// N = 1
		sendDisplayMessage(String
				.format("\t\tThe order of the modular exponential, " + "referred to as the order of a mod N or ord(a),"
						+ "\n\t\tis the smallest positive integer r such that a^r mod N = 1"));

		sendDisplayMessage(String.format("\t\tStarting the quantum portion of the algorithm"));

		// Step a: estimate the phase
		sendDisplayMessage(String.format("\t\t a) Estimate Phase"));

		double estimatedPhase = estimatePhase(a, n);
		sendDisplayMessage(String.format("\t\t  Estimated Phase = %s", estimatedPhase));

		// step b: find the period (in lowest terms) of the function by using a
		// continued fraction expansion
		sendDisplayMessage(String.format("\t\t b) Perform Continued Fraction Expansion"));
		int order = MathUtils.continuedFraction(estimatedPhase, a, n);

		sendDisplayMessage(String.format("\t\t Order calculated is %s ", order));

		return order;

	}

	/**
	 * calculates the estimated phase using quantum algorithm
	 * 
	 * 
	 * Design Pattern: Pipeline
	 */
	private double estimatePhase(int a, int n) throws ExceedsMaxQuibitsSupportedException {

		// creating invoker for the commands
		QuantumOperationInvoker quantumOperationInvoker = new QuantumOperationInvoker();

		List<PipelineStep> algorithmStepsPipeline = new ArrayList<>();

		// The algorithm uses two quantum registers:

		// Declare 2 quantum registers with sizes such that
		// source register 1 (source) has K qubits and stores a number Q = 2^K, with
		// N^2 ≤ Q ≤ 2N^2 or equivalently a number mod Q K ≤ 1+2log2N
		int sourceRegisterSize = (int) (2 * (1 + (Math.log(n) / Math.log(2))));

		// target register 2 (target) has at least n = log2N qubits, so can store N or
		// more basis states, or equivalently, a number mod N n ≤ log2N.
		int targetRegisterSize = (int) (1 + (Math.log(n) / Math.log(2)));

		sendDisplayMessage(String.format(
				"\t\t  Declare 2 quantum registers, source and target with %s and %s quibits respectively",
				sourceRegisterSize, targetRegisterSize));

		// declare Source quantum register
		QuantumRegisterInterface sourceRegister = new QuantumRegister(sourceRegisterSize);

		// declare target Quantum Register
		QuantumRegister targetRegister = new QuantumRegister(targetRegisterSize);

		getAlgorithmStepsPipeline(a, n, algorithmStepsPipeline, sourceRegister, targetRegister);

		// Pipeline Pattern
		for (int i = 0; i < algorithmStepsPipeline.size(); i++) {
			sendDisplayMessage(algorithmStepsPipeline.get(i).getStepDescription());
			quantumOperationInvoker.executeOperation(algorithmStepsPipeline.get(i).stepCommand);
		}

		// step 9
		// read source register bit by bit to get phase
		sendDisplayMessage(String.format("\t\t  Read source register and return the estimated phase."));

		double estimatedPhase = Double.valueOf(sourceRegister.getMeasuredValue()) / Math.pow(2, sourceRegisterSize);

		return estimatedPhase;
	}

	/**
	 * constructs the pipline of algorithm steps
	 * 
	 * 
	 * Design Pattern: Pipeline
	 */
	private List<PipelineStep> getAlgorithmStepsPipeline(int a, int n, List<PipelineStep> algorithmStepsPipeline,
			QuantumRegisterInterface sourceRegister, QuantumRegister targetRegister) {
		// Todo Caveat, pattern could be messed up, if you wire the steps incorrectly,
		// need to consider that when refactoring

		PipelineStep step1 = new PipelineStep(1, new InitialStateQuantumOperation(sourceRegister),
				String.format("\t\t  Step i: Initialize the source register to |0>"));
		PipelineStep step2 = new PipelineStep(2, new InitialStateQuantumOperation(targetRegister),
				String.format("\t\t  Step ii: Initialize the target register to |0>"));
		PipelineStep step3 = new PipelineStep(3, new HadamardQuantumOperation(sourceRegister), String.format(
				"\t\t  Step iii: Transform the source register to an equal superposition over all basis states\n\t\t\t\tby applying the Hadamard transform"));
		PipelineStep step4 = new PipelineStep(4, new QuantumFourierTransformQuantumOperation(sourceRegister),
				String.format("\t\t  Step iv: Apply Quantum Fourier Transform"));
		PipelineStep step5 = new PipelineStep(5,
				new ModularExponentiationQuantumOperation(sourceRegister, targetRegister, a, n), String.format(
						"\t\t  Step v: Apply a function that implements modular exponentiation to the contents of source\n\t\t\t\tregister and store the result in target register."));
		PipelineStep step6 = new PipelineStep(6, new MeasurementQuantumOperation(targetRegister),
				String.format("\t\t  Step vi: Measure target register"));
		PipelineStep step7 = new PipelineStep(7, new QuantumFourierTransformQuantumOperation(sourceRegister),
				String.format("\t\t  Step vii: Apply QFT on source register"));
		PipelineStep step8 = new PipelineStep(8, new MeasurementQuantumOperation(sourceRegister),
				String.format("\t\t  Step viii: Measure source register"));

		algorithmStepsPipeline.add(step1);
		algorithmStepsPipeline.add(step2);
		algorithmStepsPipeline.add(step3);
		algorithmStepsPipeline.add(step4);
		algorithmStepsPipeline.add(step5);
		algorithmStepsPipeline.add(step6);
		algorithmStepsPipeline.add(step7);
		algorithmStepsPipeline.add(step8);

		return algorithmStepsPipeline;
	};

}
