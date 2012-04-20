package com.tastymonster.automation.page.base;

import com.tastymonster.automation.element.base.*;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

public class BaseLandingPage extends AbstractAutomationPage {
	public final DivWebElement wrapper = new DivWebElement( "wrapper", this ); // divStart
	public final DivWebElement welcome = new DivWebElement( "welcome", this ); // divStartBox
	public final DivWebElement page = new DivWebElement( "page", this ); // divStart
	public final DivWebElement content = new DivWebElement( "content", this ); // divStart

		BaseLandingPage() {
	}
	
	public String getPageURI() {
		return "landing.vm";
	}
}