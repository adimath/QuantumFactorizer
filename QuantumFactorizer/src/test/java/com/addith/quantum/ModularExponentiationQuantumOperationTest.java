package com.addith.quantum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mockito;

import com.addith.utils.ExceedsMaxQuibitsSupportedException;

class ModularExponentiationQuantumOperationTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager
			.getLogger(ModularExponentiationQuantumOperationTest.class.getName());

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
	}

	@org.junit.jupiter.api.AfterEach
	void tearDown() {
	}

	@org.junit.jupiter.api.Test
	void apply() {

		try {
			QuantumOperationInvoker quantumOperationInvoker = new QuantumOperationInvoker();
			QuantumRegisterInterface registerMock = Mockito.mock(QuantumRegisterInterface.class);
			QuantumRegister targetRegister = new QuantumRegister(0);
			ModularExponentiationQuantumOperation modularExponentiationQuantumOperation = new ModularExponentiationQuantumOperation(
					registerMock, targetRegister, 17, 13);
			quantumOperationInvoker.executeOperation(modularExponentiationQuantumOperation);

			Mockito.verify(registerMock, Mockito.times(1)).performModularExponentiation(targetRegister, 17, 13);
		} catch (ExceedsMaxQuibitsSupportedException e) {
			logger.error("apply()", e); //$NON-NLS-1$
		}

	}
}