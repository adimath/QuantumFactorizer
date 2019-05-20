package com.addith.quantum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HadamardQuantumOperationTest {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void apply() {

		QuantumOperationInvoker quantumOperationInvoker = new QuantumOperationInvoker();
		QuantumRegisterInterface registerMock = Mockito.mock(QuantumRegisterInterface.class);
		HadamardQuantumOperation hadamardQuantumOperation = new HadamardQuantumOperation(registerMock);
		quantumOperationInvoker.executeOperation(hadamardQuantumOperation);

		Mockito.verify(registerMock, Mockito.times(1)).applyHadamard();

	}
}
