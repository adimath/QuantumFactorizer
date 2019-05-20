
package com.addith.quantum;

/**
 * The Class State data structure representing quantum states in the register.
 */

public class ComplexState {

	public double realPart, imaginaryPart;

	/**
	 * Creates a new complex number to represent a State
	 *
	 * @param real      : the real part of the complex state
	 * @param imaginary : the imaginary part of the complex state
	 */
	public ComplexState(double real, double imaginary) {
		realPart = real;
		imaginaryPart = imaginary;
	}

	/**
	 * Returns the sum of this Complex state and the passed complex state
	 *
	 * @param toAdd the complex state to add to this complex state
	 * @return the sum
	 */
	public ComplexState add(ComplexState toAdd) {
		return new ComplexState(this.realPart + toAdd.realPart, this.imaginaryPart + toAdd.imaginaryPart);
	}

	/**
	 * Returns the sin of a complex state
	 *
	 * @param cmplxState a complex state
	 * @return sin of cmplxState
	 */
	public static ComplexState sin(ComplexState cmplxState) {
		return new ComplexState(Math.sin(cmplxState.realPart) * Math.cosh(cmplxState.imaginaryPart),
				Math.cos(cmplxState.realPart) * Math.sinh(cmplxState.imaginaryPart));
	}

	/**
	 * Returns the sin of this complex state.
	 *
	 * @return sin of this complex State
	 */
	public ComplexState sin() {
		return new ComplexState(Math.sin(realPart) * Math.cosh(imaginaryPart),
				Math.cos(realPart) * Math.sinh(imaginaryPart));
	}

	/**
	 * subtracts parameter complex state from this complex state.
	 *
	 * @param cmplxState a complex state to subtract
	 * @return the difference of cmplxState - this complex state
	 */
	public ComplexState subtract(ComplexState cmplxState) {
		return new ComplexState(this.realPart - cmplxState.realPart, this.imaginaryPart - cmplxState.imaginaryPart);
	}

}
