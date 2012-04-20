package com.tastymonster.automation.page.base;

import com.tastymonster.automation.element.base.*;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

public class BaseCreateUserPage extends AbstractAutomationPage {
	public final TextBoxWebElement usersecurityAnswer = new TextBoxWebElement( "securityAnswerField", this ); // formPassword
	public final TextBoxWebElement userlastName = new TextBoxWebElement( "lastNameField", this ); // formInput
	public final TextBoxWebElement userphone = new TextBoxWebElement( "phoneField", this ); // formInput
	public final ButtonWebElement btnSubmit = new ButtonWebElement( "btnSubmit", this ); // formButton
	public final DivWebElement wrapper = new DivWebElement( "wrapper", this ); // divStart
	public final TextBoxWebElement usersecurityQuestion = new TextBoxWebElement( "securityQuestionField", this ); // formInput
	public final TextBoxWebElement emailField = new TextBoxWebElement( "emailField", this ); // formInput
	public final TextBoxWebElement userfirstName = new TextBoxWebElement( "firstNameField", this ); // formInput
	public final ButtonWebElement guestLogin = new ButtonWebElement( "guestLogin", this ); // button

		BaseCreateUserPage() {
	}
	
	public String getPageURI() {
		return "createUser.vm";
	}
}