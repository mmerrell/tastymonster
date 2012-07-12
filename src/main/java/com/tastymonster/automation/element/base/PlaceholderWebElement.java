package com.tastymonster.automation.element.base;

import com.tastymonster.automation.page.base.AbstractAutomationPage;

public class PlaceholderWebElement extends AbstractWebElement implements IPlaceholder {

	public PlaceholderWebElement( String id, TabWebElement tab, AbstractAutomationPage page ) {
		super( id, tab, page );
	}

	public PlaceholderWebElement(String id, AbstractAutomationPage page) {
		super( id, null, page );
	}

}
