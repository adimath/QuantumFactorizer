package com.addith.utils;

/**
 * Exception that gets thrown when the size of the register exceeds the max
 * array length supported by vm on the running platform.
 */
public class ExceedsMaxQuibitsSupportedException extends Throwable {

	/**
	 * using default serial version for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Returns a detailed message explaining that max number of qubit/ register size
	 * exceeds the supported limit
	 *
	 * @param msg general user input message
	 */
	public ExceedsMaxQuibitsSupportedException(String msg) {
		super(msg);
	}

	/**
	 * Exception with no message
	 */
	public ExceedsMaxQuibitsSupportedException() {
		super();
	}

}
