package com.tastymonster.patentmojo.automation.page.base;

import com.tastymonster.patentmojo.automation.element.base.*;
import com.tastymonster.patentmojo.automation.page.base.AbstractAutomationPage;

public class BaseIndexPage extends AbstractAutomationPage {
	public final DivWebElement content = new DivWebElement( "content", this ); // divStart
	public final DivWebElement viverraAmet = new DivWebElement( "viverraAmet", this ); // divStartBox
	public final ButtonWebElement btn_Change = new ButtonWebElement( "btn_Change", this ); // button
	public final DivWebElement sidebar_upper = new DivWebElement( "sidebar_upper", this ); // divStartBox
	public final DivWebElement sidebar = new DivWebElement( "sidebar", this ); // divStart
	public final DivWebElement page = new DivWebElement( "page", this ); // divStart
	public final DivWebElement next = new DivWebElement( "next", this ); // divStartBox
	public final DivWebElement welcome = new DivWebElement( "welcome", this ); // divStartBox
	public final DivWebElement wrapper = new DivWebElement( "wrapper", this ); // divStart

		BaseIndexPage() {
	}
	
	public String getPageURI() {
		return "index.vm";
	}
}