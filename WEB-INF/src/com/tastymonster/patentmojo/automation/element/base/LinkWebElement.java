package com.tastymonster.patentmojo.automation.element.base;

import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;


public class LinkWebElement extends AbstractClickableWebElement implements IClickable {

	public LinkWebElement( String id, AbstractAutomationPage page ) {
		super( id, page );
	}

}
