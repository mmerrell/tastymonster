package com.tastymonster.patentmojo.automation.codegen;

import com.tastymonster.patentmojo.automation.element.base.AbstractWebElement;

/**
 * Contains metadata used when mapping a field type to a WebElement, but does not contain anything about the WebElement on the page itself
 * @author Marcus
 *
 */
public class WebElementDetails {

	private Class<? extends AbstractWebElement> type;
	
	public WebElementDetails( Class<? extends AbstractWebElement> type ) {
		this.type = type;
	}

	public Class<? extends AbstractWebElement> getType() {
		return type;
	}
}
