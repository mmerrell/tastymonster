package com.tastymonster.automation.element.base;

import com.tastymonster.automation.page.base.AbstractAutomationPage;



public class LinkWebElement extends AbstractClickableWebElement implements IClickable {

	public LinkWebElement( String id, AbstractAutomationPage page ) {
		super( id, page );
	}

}
