package com.tastymonster.patentmojo.automation.director.base;

import com.tastymonster.patentmojo.automation.element.base.AbstractWebElement;
import com.tastymonster.patentmojo.automation.element.base.ISettable;
import com.tastymonster.patentmojo.automation.element.base.TabWebElement;
import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public abstract class AbstractSettableWebElement<T> extends AbstractWebElement implements ISettable<T> {

	private T value;
	
	public AbstractSettableWebElement( String id, AbstractAutomationPage page ) {
		this( id, null, page );
	}
	
	public AbstractSettableWebElement(String id, TabWebElement tab, AbstractAutomationPage page ) {
		super( id, tab, page );
	}

	@Override
	public void setValue( T value ) {
		this.value = value;
	}
	
	@Override
	public void setWebValue() {
		
	}
	
	public T getWebValue() {
		return value;
	}

	@Override
	public boolean hasValue() {
		return value != null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

}
