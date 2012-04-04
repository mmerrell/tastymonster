package com.tastymonster.patentmojo.automation.page.base;

import com.tastymonster.patentmojo.automation.element.base.*;
import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public class BaseLoginPage extends AbstractAutomationPage {
	public final ButtonWebElement btn_Login = new ButtonWebElement( "btn_Login", this ); // formButton
	public final TextBoxWebElement passwordField = new TextBoxWebElement( "passwordField", this ); // formPassword
	public final ButtonWebElement btn_Register = new ButtonWebElement( "btn_Register", this ); // formButton
	public final TextBoxWebElement loginField = new TextBoxWebElement( "loginField", this ); // formInput
	public final DivWebElement login = new DivWebElement( "login", this ); // divStart

		BaseLoginPage() {
	}
	
	public String getPageURI() {
		return "login.vm";
	}
}