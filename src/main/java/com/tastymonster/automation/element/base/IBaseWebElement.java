package com.tastymonster.automation.element.base;

public interface IBaseWebElement<T> {

	/**
	 * Allows you to set a value on a Settable WebElement, but does not persist it to the browser
	 * @param value
	 */
	void setValue( T value );
	
	/**
	 * Returns the value contained by the control on the browser right now 
	 * @return
	 */
	T getWebValue();
	
}
