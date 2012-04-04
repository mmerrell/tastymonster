package com.tastymonster.patentmojo.automation.element.base;

public interface ISettable<T> extends IBaseWebElement<T> {

	/**
	 * Persists the value stored in this element to the browser
	 */
	void setWebValue();
	
	/**
	 * Returns true if 'setValue()' has been called with a value
	 * @return
	 */
	boolean hasValue();
	
	/**
	 * Indicates whether or not this element exists on the page AND is visible
	 * @return
	 */
	boolean exists();
}
