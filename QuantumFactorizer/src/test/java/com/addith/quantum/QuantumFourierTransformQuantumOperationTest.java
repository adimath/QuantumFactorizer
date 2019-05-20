package com.addith.quantum;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class QuantumFourierTransformQuantumOperationTest {

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
	void testApply() {

		QuantumOperationInvoker quantumOperationInvoker = new QuantumOperationInvoker();
		QuantumRegisterInterface registerMock = Mockito.mock(QuantumRegisterInterface.class);
		QuantumFourierTransformQuantumOperation quantumFourierTransformQuantumOperation = new QuantumFourierTransformQuantumOperation(
				registerMock);
		quantumOperationInvoker.executeOperation(quantumFourierTransformQuantumOperation);

		Mockito.verify(registerMock, Mockito.times(1)).quantumFourierTransform();

	}

}
