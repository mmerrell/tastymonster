package com.tastymonster.patentmojo.automation.element.base;

import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public class TextBoxWebElement extends AbstractSettableWebElement<String> implements ITextBox {

	public TextBoxWebElement( String id, AbstractAutomationPage page ) {
		super( id, page );
	}

	@Override
	public void setWebValue() {
		this.getWebElement().sendKeys( this.getValue() );
	}
}
