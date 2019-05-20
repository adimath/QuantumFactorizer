package com.addith.quantum;

import org.mockito.Mockito;

class MeasurementQuantumOperationTest {

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
		MeasurementQuantumOperation measurementQuantumOperation = new MeasurementQuantumOperation(registerMock);
		quantumOperationInvoker.executeOperation(measurementQuantumOperation);

		Mockito.verify(registerMock, Mockito.times(1)).measure();

	}
}