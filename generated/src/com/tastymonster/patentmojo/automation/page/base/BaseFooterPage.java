package com.tastymonster.patentmojo.automation.page.base;

import com.tastymonster.patentmojo.automation.element.base.*;
import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public class BaseFooterPage extends AbstractAutomationPage {
	public final DivWebElement footer = new DivWebElement( "footer", this ); // divStart

		BaseFooterPage() {
	}
	
	public String getPageURI() {
		return "footer.vm";
	}
}