package com.tastymonster.automation.page.base;

import com.tastymonster.automation.element.base.*;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

public class BaseAddPatentPage extends AbstractAutomationPage {
	public final TextBoxWebElement patentinventorTitle = new TextBoxWebElement( "inventorTitle", this ); // formInput
	public final TextBoxWebElement patentdateConceived = new TextBoxWebElement( "dateConceived", this ); // formInput
	public final ButtonWebElement btnSubmit = new ButtonWebElement( "btnSubmit", this ); // formButton
	public final TextBoxWebElement titleField = new TextBoxWebElement( "titleField", this ); // formInput
	public final TextBoxWebElement patentcompanyName = new TextBoxWebElement( "companyName", this ); // formInput
	public final TextBoxWebElement patentinventorName = new TextBoxWebElement( "inventorName", this ); // formInput
	public final TextBoxWebElement industry = new TextBoxWebElement( "industry", this ); // formInput
	public final TextBoxWebElement descriptionField = new TextBoxWebElement( "descriptionField", this ); // formInput
	public final ButtonWebElement inventor = new ButtonWebElement( "addInventor", this ); // button
	public final DivWebElement wrapper = new DivWebElement( "wrapper", this ); // divStart

		BaseAddPatentPage() {
	}
	
	public String getPageURI() {
		return "addPatent.vm";
	}
}