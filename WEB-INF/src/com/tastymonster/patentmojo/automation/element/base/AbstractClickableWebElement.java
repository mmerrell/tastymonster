package com.tastymonster.patentmojo.automation.element.base;

import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public abstract class AbstractClickableWebElement extends AbstractWebElement {

	public AbstractClickableWebElement(String id, AbstractAutomationPage page) {
		super( id, page );
	}

	public void click() {
		
	}
}
