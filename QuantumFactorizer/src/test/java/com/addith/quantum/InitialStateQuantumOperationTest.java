package com.addith.quantum;

import org.mockito.Mockito;

/**
 * The Class InitialStateQuantumOperationTest.
 */
class InitialStateQuantumOperationTest {

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
	}

	@org.junit.jupiter.api.AfterEach
	void tearDown() {
	}

	@org.junit.jupiter.api.Test
	void apply() {

		QuantumOperationInvoker quantumOperationInvoker = new QuantumOperationInvoker();
		QuantumRegisterInterface registerMock = Mockito.mock(QuantumRegisterInterface.class);
		InitialStateQuantumOperation initialStateQuantumOperation = new InitialStateQuantumOperation(registerMock);
		quantumOperationInvoker.executeOperation(initialStateQuantumOperation);

		Mockito.verify(registerMock, Mockito.times(1)).applyInitialState();

	}
}