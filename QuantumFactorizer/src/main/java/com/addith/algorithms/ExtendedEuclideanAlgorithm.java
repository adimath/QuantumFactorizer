
package com.addith.algorithms;

/**
 * Implements the extended Euclidean algorithm.
 * 

 *
 */
public class ExtendedEuclideanAlgorithm extends Algorithm {

	/**
	 * Instantiates a new extended Euclidean algorithm.
	 */
	public ExtendedEuclideanAlgorithm() {
		super();
	}

	/**
	 * Accepts two positive numbers p and q two command line parameters p and q and
	 * calculates the greatest common divisor of p and q using the extended Euclid's
	 * algorithm. returns an array of 3 numbers, the coefficients a and b such that
	 * a(p) + b(q) = gcd(p, q), as well as the gcd of p and q return array [gcd, a,
	 * b] such that gcd = CalculateGcd(p, q), ap + bq = gcd Also prints out integers
	 * a and b such that a(p) + b(q) = gcd(p, q).
	 *
	 * @param p the p
	 * @param q the q
	 * @return the int[]
	 */
	public int[] execute(int p, int q) {
		if (q == 0) {
			return new int[] { p, 1, 0 };
		}

		int[] vals = execute(q, p % q);
		int gcd = vals[0];
		int a = vals[2];
		int b = vals[1] - (p / q) * vals[2];
		return new int[] { gcd, a, b };
	}

	/**
	 * Returns the greatest common divisor.
	 *
	 * @param p the p
	 * @param q the q
	 * @return the gcd
	 */
	public int getGcd(int p, int q) {
		int[] gcdWithCoefficients = execute(p, q);
		return gcdWithCoefficients[0];
	}
}