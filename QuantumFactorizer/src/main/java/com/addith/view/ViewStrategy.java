/**
 * 
 */
package com.addith.view;

import java.util.Observer;

/**
 * "Strategy" interface; part of the Strategy Design Pattern. We use the
 * strategy pattern to define view, so additional view types can be added easily
 * for the same back end.
 * 
 * It declares an interface common to all supported views. View Context uses
 * this interface to launch the view defined by a Concrete view Strategy.
 * 
 * This interface also implements Observer as part of the Observer Design
 * Pattern. Observer design pattern is used for the run time updates to the view
 * console without having to wait for the process to finish.
 * 
 * Design Pattern: Observer, Strategy
 */
public interface ViewStrategy extends Observer {

	/**
	 * This method declaration must be implemented by the ConcreteStrategy
	 * implementations.
	 * 
	 */
	public void launchView();

	/**
	 * Display message.
	 *
	 * @param displayString the display string
	 */
	void displayMessage(String displayString);

	/**
	 * Display error.
	 *
	 * @param displayError the display error
	 */
	void displayError(String displayError);

	/**
	 * Display welcome.
	 *
	 * @param displayWelcome the display welcome
	 */
	void displayWelcome(String displayWelcome);

}
