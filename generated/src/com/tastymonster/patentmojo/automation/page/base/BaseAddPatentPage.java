package com.tastymonster.patentmojo.automation.page.base;

import com.tastymonster.patentmojo.automation.element.base.*;
import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public class BaseAddPatentPage extends AbstractAutomationPage {
	public final TextBoxWebElement titleField = new TextBoxWebElement( "titleField", this ); // formInput
	public final ButtonWebElement btnSubmit = new ButtonWebElement( "btnSubmit", this ); // formButton
	public final TextBoxWebElement descriptionField = new TextBoxWebElement( "descriptionField", this ); // formPassword

		BaseAddPatentPage() {
	}
	
	public String getPageURI() {
		return "addPatent.vm";
	}
}