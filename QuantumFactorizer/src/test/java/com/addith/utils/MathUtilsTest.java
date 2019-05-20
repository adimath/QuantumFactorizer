package com.addith.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class MathUtilsTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@ParameterizedTest
	@ValueSource(ints = { -1, -3, -5, -3, 15, -Integer.MAX_VALUE })
	void testIspowerFactorForBoundaryNegative(int n) {
		int expectedInput = 0;
		Assertions.assertEquals(expectedInput, MathUtils.ispowerFactor(n),
				"The test for power factor with negative input failed and did not match the expected output");
	}

	@Test
	void testIspowerFactor() {
		int n = 81;
		int expectedInput = 3;
		Assertions.assertEquals(expectedInput, MathUtils.ispowerFactor(n),
				"The test for power factor with high input failed and did not match the expected output");
	}

	@ParameterizedTest
	@ValueSource(ints = { -1, 0, 1 })
	void testIsAlmostIntCheckBoundary(int x) {
		boolean expectedInput = true;
		Assertions.assertEquals(expectedInput, MathUtils.isAlmostInt(x),
				"The test for power factor for the boundary conditions failed and did not match the expected output");
	}

	@ParameterizedTest
	@CsvSource({ "-20, 5", "0, 0", "-1, -1", "1, 1", "999, 999" })
	void testModForZero(int n, int m) {
		int expectedInput = 0;
		Assertions.assertEquals(expectedInput, MathUtils.mod(n, m), "Expected output did not match the real output");
	}

	@ParameterizedTest
	@CsvSource({ "1, 2", "3, 2", "11, 2", "101, 2", "571, 2" })
	void testModReturnSameValue(int n, int m) {
		int expectedInput = 1;
		Assertions.assertEquals(expectedInput, MathUtils.mod(n, m), "Expected output did not match the real output");
	}

	@ParameterizedTest
	@CsvSource({ "8, 10", "11, 22", "11, 46" })
	void testModForGetOriginalNumber(int n, int m) {
		int expectedInput = n;
		Assertions.assertEquals(expectedInput, MathUtils.mod(n, m), "Expected output did not match the real output");
	}

	@Test
	void testModGetOneForNegative() {
		int n = -19;
		int m = 5;
		int expectedInput = 1;
		Assertions.assertEquals(expectedInput, MathUtils.mod(n, m), "Expected output did not match the real output");
	}

	@Test
	void testModGetNegativeForNegative() {
		int n = -9;
		int m = -5;
		int expectedInput = -4;
		Assertions.assertEquals(expectedInput, MathUtils.mod(n, m),
				"Expected user output did not match the real outputput for the negative values");
	}

	@Test
	void testModForPrimes() {
		int expectedInput = 3;
		Assertions.assertEquals(expectedInput, MathUtils.mod(29, 13),
				"Expected user output did not match the real prime input and the output for the negative values");
	}

}