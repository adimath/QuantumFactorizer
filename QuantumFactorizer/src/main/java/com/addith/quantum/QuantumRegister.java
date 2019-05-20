/*
 * 
 */
package com.addith.quantum;

import java.math.BigInteger;

/*
 * QuantumRegister.java - Class representing a quantum register
 
 */

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.addith.utils.ExceedsMaxQuibitsSupportedException;

/**
 * This class represents the quantum register. It holds 2^K the quantum states
 * where K is the size/number of Qubits on this register.
 * 
 * 
 * Register is the "Receiver" implementation for our command design pattern. It
 * knows how to perform the operations associated with the Quantum Operations
 * requested via the quantum operations implementing the Quantum Operation
 * Command.
 * 
 * Design pattern: Command
 */
public class QuantumRegister implements QuantumRegisterInterface {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(QuantumRegister.class.getName());

	/**
	 * The accuracy for double calculations. It is set to Min Value of double.
	 */
	private final static double Tolerance = Double.MIN_VALUE;

	/** Register size - The number of qubits of this register. */
	private int numberOfQubits;

	/** The states. */
	private ComplexState[] states;
	/**
	 * Map of a list containing the numbers of states being entangled with each
	 * other.
	 */
	private HashMap<Integer, ArrayList<Integer>> entanglement;

	/** The number of states stored. */
	private int numberOfStatesStored;

	/** The measured value. */
	private int measuredValue;

	/**
	 * Creates a register of <i>n</i> qubits, initialized to the state |0&gt;. A
	 * register of numberOfQubits <i>n</i> enables a total of <i>q</i> = 2
	 * <sup><i>n</i></sup> quantum states as the computational basis.
	 *
	 * @param size the size
	 * @throws ExceedsMaxQuibitsSupportedException the exceeds max quibits supported
	 *                                             exception
	 */
	public QuantumRegister(int size) throws ExceedsMaxQuibitsSupportedException {

		try {
			this.numberOfQubits = size;

			this.numberOfStatesStored = size > 0 ? (int) Math.pow(2, size) : 0;

			states = new ComplexState[this.numberOfStatesStored];
			for (int i = 0; i < this.numberOfStatesStored; i++) {
				states[i] = new ComplexState(0.0, 0.0);
			}
		} catch (java.lang.OutOfMemoryError outofMemerror) {

			logger.error("QuantumRegister(int)", outofMemerror); //$NON-NLS-1$

			throw new ExceedsMaxQuibitsSupportedException(String.format("The number of quibits needed for "
					+ "this register %s exceeds the max allowed by the current platform.", size));

		} catch (Exception e) {

			logger.error("QuantumRegister(int)", e); //$NON-NLS-1$
			throw e;
		}

	}

// getters/setters

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#getMeasuredValue()
	 */
	@Override
	public int getMeasuredValue() {
		return measuredValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#getNumberOfQubits()
	 */
	@Override
	public int getNumberOfQubits() {
		return numberOfQubits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#setNumberOfQubits(int)
	 */
	@Override
	public void setNumberOfQubits(int numberOfQubits) {
		this.numberOfQubits = numberOfQubits;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#getStates()
	 */
	@Override
	public ComplexState[] getStates() {
		return states;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.addith.quantum.QuantumRegisterInterface#setStates(com.addith.quantum.
	 * ComplexState[])
	 */
	@Override
	public void setStates(ComplexState[] states) {
		this.states = states;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#getNumberOfStatesStored()
	 */
	@Override
	public int getNumberOfStatesStored() {
		return numberOfStatesStored;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#setNumberOfStatesStored(int)
	 */
	@Override
	public void setNumberOfStatesStored(int numberOfStatesStored) {
		this.numberOfStatesStored = numberOfStatesStored;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#setMeasuredValue(int)
	 */
	@Override
	public void setMeasuredValue(int measuredValue) {
		this.measuredValue = measuredValue;
	}

	// register operations

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#applyInitialState()
	 */
	@Override
	public void applyInitialState() {
		if (this.numberOfStatesStored > 0) {
			states[0].realPart = 1;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#applyHadamard()
	 */
	@Override
	public void applyHadamard() {
		for (int i = 0; i < this.numberOfQubits; i++) {
			double realTmp, imaginaryTmp;

			for (int k = 0; k < (int) Math.pow(2, this.numberOfQubits); k += (int) Math.pow(2, i)) {
				for (int l = 0; l < (int) Math.pow(2, i - 1); l++) {
					int i0 = k + l;
					int i1 = k + l + (int) Math.pow(2, i - 1);

					// states
					realTmp = this.states[i0].realPart;
					imaginaryTmp = this.states[i0].imaginaryPart;

					this.states[i0].realPart = (realTmp + this.states[i1].realPart) / Math.sqrt(2);
					this.states[i0].imaginaryPart = (imaginaryTmp + this.states[i1].imaginaryPart) / Math.sqrt(2);

					this.states[i1].realPart = (realTmp - this.states[i1].realPart) / Math.sqrt(2);
					this.states[i1].imaginaryPart = (imaginaryTmp - this.states[i1].imaginaryPart) / Math.sqrt(2);
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#measure()
	 */
	@Override
	public int measure() {

		// Returns a double value with a positive sign, greater than or equal to 0.0 and
		// less than 1.0
		double randomNumber = Math.random();

		int valueIndex = -1;
		double probability = 0.0;

		do {
			valueIndex++;
			// states
			probability = states[valueIndex].realPart * states[valueIndex].realPart
					+ states[valueIndex].imaginaryPart * states[valueIndex].imaginaryPart;

			randomNumber -= probability;
		} while (randomNumber >= 0);

		// collapse:
		states = new ComplexState[this.numberOfStatesStored];
		for (int i = 0; i < this.numberOfStatesStored; i++) {
			states[i] = new ComplexState(0.0, 0.0);
		}

		states[valueIndex].realPart = 1;
		this.measuredValue = valueIndex;

		return valueIndex;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.addith.quantum.QuantumRegisterInterface#performModularExponentiation(com.
	 * addith.quantum.QuantumRegister, int, int)
	 */

	@Override
	public QuantumRegisterInterface performModularExponentiation(QuantumRegister targetRegister, int a, int n) {
		try {
			int x = 0;
			while (x < states.length) {
				if (Math.abs(states[x].realPart) < Tolerance && Math.abs(states[x].imaginaryPart) < Tolerance) {
					return targetRegister;
				}
				x++;
			}

			entanglement = new HashMap<Integer, ArrayList<Integer>>();

			int targetRegisterSize = (int) Math.pow(2, targetRegister.numberOfQubits);
			ComplexState[] statesTemp = new ComplexState[targetRegisterSize];
			for (int i = 0; i < targetRegisterSize; i++) {
				statesTemp[i] = new ComplexState(0.0, 0.0);
			}

			int index, m;
			int intModPow;
			double phase = 0, tmp;

			for (x = 0; x < states.length; x++) {

				// note that The modPow method will only accept a negative exponent
				// -x if a and p are coprime. we
				// have already verified they are coprimes before getting here
				BigInteger bigModPow = BigInteger.valueOf(a).modPow(BigInteger.valueOf(x), BigInteger.valueOf(n));

				intModPow = bigModPow.intValue();

				phase = Math.atan2(targetRegister.states[intModPow].imaginaryPart,
						targetRegister.states[intModPow].realPart);

				for (m = 0; m < statesTemp.length; m++) {
					if (Math.abs(targetRegister.states[m].realPart) < Tolerance
							&& Math.abs(targetRegister.states[m].imaginaryPart) < Tolerance) {
						continue;
					}
					index = m ^ intModPow;
//					if (index < 0 || index >= targetRegister.states.length) {
//						throw new java.nio.BufferOverflowException();
//					}

					if (Math.abs(targetRegister.states[index].realPart) < Tolerance
							&& Math.abs(targetRegister.states[index].realPart) < Tolerance) {
						statesTemp[index].realPart = 1;

					} else {
						statesTemp[index].realPart = targetRegister.states[index].realPart * Math.cos(phase)
								- targetRegister.states[index].imaginaryPart * Math.sin(phase);
						statesTemp[index].imaginaryPart = targetRegister.states[index].imaginaryPart * Math.cos(phase)
								+ targetRegister.states[index].realPart * Math.sin(phase);
					}

					if (!entanglement.containsKey(index)) {
						entanglement.put(index, new ArrayList<Integer>());
					}
					entanglement.get(index).add(x);
				}

				tmp = this.states[x].realPart;
				this.states[x].realPart = tmp * Math.cos(phase) - this.states[x].imaginaryPart * Math.sin(phase);
				this.states[x].imaginaryPart = this.states[x].imaginaryPart * Math.cos(phase) + tmp * Math.sin(phase);
			}

			double length = 0;

			targetRegister.states = new ComplexState[targetRegisterSize];
			for (int i = 0; i < targetRegisterSize; i++) {
				targetRegister.states[i] = new ComplexState(0.0, 0.0);
			}

			for (int k : entanglement.keySet()) {
				targetRegister.states[k].realPart = statesTemp[k].realPart;
				targetRegister.states[k].imaginaryPart = statesTemp[k].imaginaryPart;
				length += targetRegister.states[k].realPart * targetRegister.states[k].realPart;
			}
			length = Math.sqrt(length);

			for (int k : entanglement.keySet()) {
				targetRegister.states[k].realPart = targetRegister.states[k].realPart / length;
			}

			return targetRegister;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.addith.quantum.QuantumRegisterInterface#quantumFourierTransform()
	 */
	@Override
	public void quantumFourierTransform() {

		// fast Fourier transform of the entire register
		try {

			double scalarFactor = Math.sqrt(1.0 / this.numberOfStatesStored);

			int index = 0;
			for (int k = 0; k < this.numberOfStatesStored; k++) {
				if (index >= k) {
					double tempReal = states[index].realPart * scalarFactor;
					double tempImaginary = states[index].imaginaryPart * scalarFactor;
					states[index].realPart = states[k].realPart * scalarFactor;
					states[index].imaginaryPart = states[k].imaginaryPart * scalarFactor;
					states[k].realPart = tempReal;
					states[k].imaginaryPart = tempImaginary;
				}
				int halfstateSize = this.numberOfStatesStored / 2;
				while (halfstateSize >= 1 && index >= halfstateSize) {
					index -= halfstateSize;
					halfstateSize /= 2;
				}
				index += halfstateSize;
			}

			// Danielson Lanczos Lemma
			// The discrete Fourier transform of length N (where N is even) can be rewritten
			// as the sum of two discrete Fourier transforms, each of length N/2. One is
			// formed from the even-numbered points; the other from the odd-numbered points.

			int stepIndex = 2;
			for (int p = 1; p < this.numberOfStatesStored; stepIndex = 2 * p) {
				double delta = Math.PI / p;
				for (int m = 0; m < p; m++) {
					// Recurrence per Trig
					double theta = m * delta;
					double cosTheta = Math.cos(theta);
					double sinTheta = Math.sin(theta);
					for (int i = m; i < this.numberOfStatesStored; i += stepIndex) {
						index = i + p;
						double realTemp = cosTheta * states[index].realPart - sinTheta * states[index].imaginaryPart;
						double imaginaryTemp = cosTheta * states[index].imaginaryPart
								+ sinTheta * states[index].realPart;

						states[index].realPart = states[i].realPart - realTemp;
						states[index].imaginaryPart = states[i].imaginaryPart - imaginaryTemp;
						states[i].realPart += realTemp;
						states[i].imaginaryPart += imaginaryTemp;
					}
				}
				p = stepIndex;
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}
}