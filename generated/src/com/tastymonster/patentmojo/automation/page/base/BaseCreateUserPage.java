package com.tastymonster.patentmojo.automation.page.base;

import com.tastymonster.patentmojo.automation.element.base.*;
import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public class BaseCreateUserPage extends AbstractAutomationPage {
	public final TextBoxWebElement securityAnswerField = new TextBoxWebElement( "securityAnswerField", this ); // formPassword
	public final TextBoxWebElement firstNameField = new TextBoxWebElement( "firstNameField", this ); // formInput
	public final DivWebElement wrapper = new DivWebElement( "wrapper", this ); // divStart
	public final TextBoxWebElement securityQuestionField = new TextBoxWebElement( "securityQuestionField", this ); // formInput
	public final TextBoxWebElement lastNameField = new TextBoxWebElement( "lastNameField", this ); // formInput
	public final TextBoxWebElement emailField = new TextBoxWebElement( "emailField", this ); // formInput
	public final DivWebElement standard_form = new DivWebElement( "standard_form", this ); // divStart
	public final ButtonWebElement btnSubmit = new ButtonWebElement( "btnSubmit", this ); // formButton
	public final TextBoxWebElement phoneField = new TextBoxWebElement( "phoneField", this ); // formInput

		BaseCreateUserPage() {
	}
	
	public String getPageURI() {
		return "createUser.vm";
	}
}