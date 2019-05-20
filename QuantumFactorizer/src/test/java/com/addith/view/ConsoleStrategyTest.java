/**
 * 
 */
package com.addith.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 */
class ConsoleStrategyTest extends ConsoleStrategy {

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
	 * Test method for {@link com.addith.view.ConsoleStrategy#launchView()}.
	 */
	@Test
	void testLaunchViewCheckIntegerInputPass() {

		// Could not test the method because of testability issues.
		// Todo: code must be changed to be more testable

	}

	/**
	 * Test method for {@link com.addith.view.ConsoleStrategy#launchView()}.
	 */
	@Test
	void testParseArgumentFail() {
		int expectedInput = 21;
		String userInput = "22";
		ConsoleStrategy instance = new ConsoleStrategy();
		int returnedInput = instance.parseArgmument(userInput);
		Assertions.assertNotEquals(expectedInput, returnedInput,
				"Expected user input match. It should not have matched.");
	}

	/**
	 * Test method for {@link com.addith.view.ConsoleStrategy#launchView()}.
	 */
	@Test
	void testParseArgumentPass() {
		int expectedInput = 21;
		String userInput = "21";
		ConsoleStrategy instance = new ConsoleStrategy();
		int returnedInput = instance.parseArgmument(userInput);
		Assertions.assertEquals(expectedInput, returnedInput, "Expected user input did not match the real input");
	}
}