package com.addith.algorithms;

import java.util.HashMap;
import java.util.Observable;

import com.addith.utils.MessageCategory;

/**
 * The Class Algorithm. This class is the base class for other algorithms like
 * Shors and quantum order finding algorithm. I
 * 
 * Design Pattern: Observer It extends Observable as part of the Observer
 * Pattern. We use the Observer pattern for the update of algorithm steps as
 * they are executed.
 */
public abstract class Algorithm extends Observable {

	/**
	 * Instantiates a new algorithm.
	 */
	public Algorithm() {
		super();
	}

	/** The message severity. */
	int messageSeverity = 0;

	/** The error severity. */
	int errorSeverity = 1;

	/** The display message. */
	public String displayMessage;

	/**
	 * Send display message.
	 *
	 * @param stringMessage the string message
	 */
	protected void sendDisplayMessage(String stringMessage) {

		setChanged();
		HashMap<MessageCategory, String> myHashMap = new HashMap<>();
		myHashMap.put(MessageCategory.INFORMATION, stringMessage);
		notifyObservers(myHashMap);
	}

	/**
	 * Send initial display message.
	 *
	 * @param stringMessage the string message
	 */
	protected void sendInitialDisplayMessage(String stringMessage) {

		setChanged();
		HashMap<MessageCategory, String> myHashMap = new HashMap<>();
		myHashMap.put(MessageCategory.WELCOME, stringMessage);
		notifyObservers(myHashMap);
	}

	/**
	 * Send error message.
	 *
	 * @param stringMessage the string message
	 */
	protected void sendErrorMessage(String stringMessage) {

		setChanged();
		HashMap<MessageCategory, String> myHashMap = new HashMap<>();
		myHashMap.put(MessageCategory.ERROR, stringMessage);
		notifyObservers(myHashMap);
	}
}