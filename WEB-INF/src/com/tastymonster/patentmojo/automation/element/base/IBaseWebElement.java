package com.tastymonster.patentmojo.automation.element.base;

public interface IBaseWebElement<T> {

	void setValue( T value );
	
	T getWebValue();
	
}
