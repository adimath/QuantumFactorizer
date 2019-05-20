package com.addith.quantum;

import org.mockito.Mockito;

class QuantumOperationInvokerTest {

	@org.junit.jupiter.api.Test
	void executeOperation() {
	}

	@org.junit.jupiter.api.Test
	void storeCommand() {
	}

	@org.junit.jupiter.api.Test
	void execute() {

		QuantumOperationInvoker quantumOperationInvoker = new QuantumOperationInvoker();
		QuantumOperationCommand quantumOperationCommandMock = Mockito.mock(QuantumOperationCommand.class);
		quantumOperationInvoker.executeOperation(quantumOperationCommandMock);

		Mockito.verify(quantumOperationCommandMock, Mockito.times(1)).apply();

	}
}