/**
 * 
 */
package com.addith.algorithms;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.addith.utils.ExceedsMaxQuibitsSupportedException;
import com.addith.utils.MathUtils;

/**
 *
 */
class ModifiedShorsAlgorithmTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ModifiedShorsAlgorithmTest.class.getName());

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.addith.algorithms.ModifiedShorsAlgorithm#execute(int)}.
	 */
	@Test
	void testExecuteShor() {
	}

	/**
	 * Test method for
	 * {@link com.addith.algorithms.ModifiedShorsAlgorithm#getFactor(int)}.
	 */
	@Test
	void testGetFactorShorcase15() {

		int numberToFactor = 15;

		int expectedFactor1 = 5;
		int expectedFactor2 = 3;

		List<Integer> expectedFactors = new ArrayList<>();
		expectedFactors.add(expectedFactor1);
		expectedFactors.add(expectedFactor2);

		try {
			int factor = new ModifiedShorsAlgorithm().getFactor(numberToFactor);

			Assertions.assertTrue(expectedFactors.contains(factor),
					"Expected factors do not match calculated factors.");

		} catch (Exception | ExceedsMaxQuibitsSupportedException e) {
			logger.error("testGetFactorShorcase15()", e); //$NON-NLS-1$
		}
	}

	/**
	 * Test method for
	 * {@link com.addith.algorithms.ModifiedShorsAlgorithm#getFactor(int)}.
	 */
	@Test
	void testGetFactorShorcase17() {

		int numberToFactor = 17;

		int expectedFactor1 = -1;

		try {
			int actualFactor = new ModifiedShorsAlgorithm().getFactor(numberToFactor);

			Assertions.assertEquals(expectedFactor1, actualFactor,
					"Expected output does not match factor for prime numbers returning negative shore value.");

		} catch (Exception | ExceedsMaxQuibitsSupportedException e) {
			logger.error("testGetFactorShorcase17()", e); //$NON-NLS-1$
		}
	}

	@ParameterizedTest
	@CsvSource({ "-1, -2", "-200, -2", "-131, -2", "-54, -2", "-35, -2", "-61, -2", "-27, -2", "-8, -2", "-569, -2",
			"-110, -2" })
	void testGetFactorCheckForNegative(int n, int m) {
		int expectedInput = m;
		try {
			Assertions.assertEquals(expectedInput, new ModifiedShorsAlgorithm().getFactor(n),
					"Expected output does not match factor checked for negative numbers.");
		} catch (Exception | ExceedsMaxQuibitsSupportedException e) {
			logger.error("testGetFactorCheckForNegative(int, int)", e); //$NON-NLS-1$
		}
	}

	@ParameterizedTest
	@CsvSource({ "7, 0", "5, 0", "11, 0", "31, 0" })
	void testGetFactorForPrime(int n, int m) {
		int expectedInput = m;
		Assertions.assertEquals(expectedInput, MathUtils.ispowerFactor(n),
				"Expected output does not match factor for prime numbers.");
	}
}