/**
 * 
 */
package com.addith.view;

/**
 * "Context" implementation.
 * <ul>
 * <li>is configured with a Concrete interaction Strategy object.</li>
 * <li>maintains a reference to a Interaction strategy object.</li>
 * <li>may define an interface that lets Strategy access its data.</li>
 * </ul>
 * 
 * * Design Pattern: Strategy
 * 
 */
public class ViewContext {

	/** stores the Interaction Strategy instance of the Context */
	private ViewStrategy strategy; // should this be final

	/**
	 * Constructor.
	 *
	 * @param selectedStrategy the selected strategy
	 */
	public ViewContext(ViewStrategy selectedStrategy) {
		super();
		this.strategy = selectedStrategy;
	}

	/**
	 * Gets the view strategy.
	 *
	 * @return the view strategy
	 */
	public ViewStrategy getViewStrategy() {

		return this.strategy;

	}

	/**
	 * This method invokes the algorithm interface of the current Strategy
	 */
	public void invokeContextStrategy() {
		strategy.launchView();
	}

}
