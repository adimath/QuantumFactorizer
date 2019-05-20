package com.addith.algorithms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.addith.algorithms.ExtendedEuclideanAlgorithm;

class ExtendedEuclideanAlgorithmTest {

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

	@Test
	void testExecuteExtendedEuclideanAlgorithm() {
	}

	@Test
	void testGetGcdNormal() {
		int number1 = 21;
		int number2 = 14;
		int expectedGCD = 7;
		ExtendedEuclideanAlgorithm instance = new ExtendedEuclideanAlgorithm();
		int returnedGCD = instance.getGcd(number1, number2);
		Assertions.assertEquals(expectedGCD, returnedGCD, "Expected GCD did not match the real GCD");
	}

	@Test
	void testGetGcdPrime() {
		int number1 = 13;
		int number2 = 3;
		int expectedGCD = 1;
		ExtendedEuclideanAlgorithm instance = new ExtendedEuclideanAlgorithm();
		int returnedGCD = instance.getGcd(number1, number2);
		Assertions.assertEquals(expectedGCD, returnedGCD, "Expected GCD did not match the real GCD");
	}

	@Test
	void testGetGcdZeroZero() {
		int number1 = 0;
		int number2 = 0;
		int expectedGCD = 0;
		ExtendedEuclideanAlgorithm instance = new ExtendedEuclideanAlgorithm();
		int returnedGCD = instance.getGcd(number1, number2);
		Assertions.assertEquals(expectedGCD, returnedGCD, "Expected GCD did not match the real GCD");
	}

	@Test
	void testGetGcdZeroOne() {
		int number1 = 0;
		int number2 = 1;
		int expectedGCD = 1;
		ExtendedEuclideanAlgorithm instance = new ExtendedEuclideanAlgorithm();
		int returnedGCD = instance.getGcd(number1, number2);
		Assertions.assertEquals(expectedGCD, returnedGCD, "Expected GCD did not match the real GCD");
	}

	@Test
	void testGetGcdOneZero() {
		int number1 = 1;
		int number2 = 0;
		int expectedGCD = 1;
		ExtendedEuclideanAlgorithm instance = new ExtendedEuclideanAlgorithm();
		int returnedGCD = instance.getGcd(number1, number2);
		Assertions.assertEquals(expectedGCD, returnedGCD, "Expected GCD did not match the real GCD");
	}

	@Test
	void testGetGcdNegOneNegOne() {
		int number1 = -1;
		int number2 = -1;
		int expectedGCD = -1;
		ExtendedEuclideanAlgorithm instance = new ExtendedEuclideanAlgorithm();
		int returnedGCD = instance.getGcd(number1, number2);
		Assertions.assertEquals(expectedGCD, returnedGCD, "Expected GCD did not match the real GCD");
	}
}