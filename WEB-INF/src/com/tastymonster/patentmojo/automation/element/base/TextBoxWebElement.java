package com.tastymonster.patentmojo.automation.element.base;

import com.tastymonster.patentmojo.automation.director.base.AbstractSettableWebElement;
import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public class TextBoxWebElement extends AbstractSettableWebElement<String> implements ITextBox {

	public TextBoxWebElement( String id, AbstractAutomationPage page ) {
		super( id, page );
	}

}
