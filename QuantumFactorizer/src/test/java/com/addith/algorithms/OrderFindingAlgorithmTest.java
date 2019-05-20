package com.addith.algorithms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.addith.utils.ExceedsMaxQuibitsSupportedException;

class OrderFindingAlgorithmTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(OrderFindingAlgorithmTest.class.getName());

	OrderFindingAlgorithm orderFindingAlgorithm;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		orderFindingAlgorithm = new OrderFindingAlgorithm();
	}

	@AfterEach
	void tearDown() throws Exception {
		orderFindingAlgorithm = null;
	}

	@Test
	@DisplayName("testExecuteBorderCases1")
	void testExecuteBorderCases1() {
		int expectedOutput = 0;
		try {
			Assertions.assertEquals(expectedOutput, new OrderFindingAlgorithm().execute(0, 0),
					"Expected output did not match the calculated output for boundary case of (0,0)");
		} catch (Exception | ExceedsMaxQuibitsSupportedException e) {
			logger.error("testExecuteBorderCases1()", e); //$NON-NLS-1$

		}
	}

	@Test
	@DisplayName("testExecuteBorderCases2")
	void testExecuteBorderCases2() {
		int expectedOutput = 0;
		try {
			Assertions.assertEquals(expectedOutput, new OrderFindingAlgorithm().execute(1, 1),
					"Expected output did not match the calculated output for boundary case of (1,1)");
		} catch (Exception | ExceedsMaxQuibitsSupportedException e) {
			logger.error("testExecuteBorderCases2()", e); //$NON-NLS-1$
		}
	}

	@ParameterizedTest
	@DisplayName("testExecuteBorderCases3")
	@CsvSource({ "-1, -1", "-5, -5", "-10, 46", "10, -4" })
	void testExecuteBorderCases3(int m, int n) {
		int expectedOutput = 0;
		try {
			Assertions.assertEquals(expectedOutput, orderFindingAlgorithm.execute(m, n),
					"Expected output did not match the calculated output for a series of negative input values");
		} catch (ExceedsMaxQuibitsSupportedException | Exception e) {
			logger.error("testExecuteBorderCases3(int, int)", e); //$NON-NLS-1$
		}
	}

	@Test
	@DisplayName("testExecute")
	void testExecute() {
		int expectedOutput = -1;
		try {
			Assertions.assertNotEquals(expectedOutput, new OrderFindingAlgorithm().execute(10, 3),
					"Expected output did not match the calculated output");
		} catch (Exception | ExceedsMaxQuibitsSupportedException e) {
			logger.error("testExecute()", e); //$NON-NLS-1$
		}
	}

	@Test
	@DisplayName("testExecutLargeInput")
	void testExecutLargeInput() {

		int expectedOutput = 10;
		try {
			Assertions.assertNotEquals(expectedOutput, new OrderFindingAlgorithm().execute(305, 591),
					"The test failed to execute supplied large input parameters.");
		} catch (Exception | ExceedsMaxQuibitsSupportedException e) {
			logger.error("testExecutLargeInput()", e); //$NON-NLS-1$
		}

	}

	@Test
	@DisplayName("testExecutFifteen")
	void testExecutFifteen() {

		try {
			Assertions.assertDoesNotThrow(() -> new OrderFindingAlgorithm().execute(4, 15),
					"The test failed because it cannot execute the supplied input parameters.");
		} catch (Exception e) {
			logger.error(e);
		}
	}
}