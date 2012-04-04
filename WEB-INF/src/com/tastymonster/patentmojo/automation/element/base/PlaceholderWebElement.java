package com.tastymonster.patentmojo.automation.element.base;

import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public class PlaceholderWebElement extends AbstractWebElement implements IStaticText {

	public PlaceholderWebElement( String id, TabWebElement tab, AbstractAutomationPage page ) {
		super( id, tab, page );
	}

	public PlaceholderWebElement(String id, AbstractAutomationPage page) {
		super( id, null, page );
	}

}
