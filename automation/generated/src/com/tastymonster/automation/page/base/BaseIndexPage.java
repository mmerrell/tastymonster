package com.tastymonster.automation.page.base;

import com.tastymonster.automation.element.base.*;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

public class BaseIndexPage extends AbstractAutomationPage {
	public final DivWebElement page = new DivWebElement( "page", this ); // divStart
	public final DivWebElement wrapper = new DivWebElement( "wrapper", this ); // divStart
	public final LinkWebElement btnChange = new LinkWebElement( "btnChange", this ); // link
	public final DivWebElement welcome = new DivWebElement( "welcome", this ); // divStartBox
	public final DivWebElement sidebar = new DivWebElement( "sidebar", this ); // divStart
	public final DivWebElement viverraAmet = new DivWebElement( "viverraAmet", this ); // divStartBox
	public final DivWebElement next = new DivWebElement( "next", this ); // divStartBox
	public final DivWebElement content = new DivWebElement( "content", this ); // divStart
	public final DivWebElement sidebarupper = new DivWebElement( "sidebarupper", this ); // divStartBox

		BaseIndexPage() {
	}
	
	public String getPageURI() {
		return "index.vm";
	}
}