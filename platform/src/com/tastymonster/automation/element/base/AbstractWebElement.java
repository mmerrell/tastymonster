package com.tastymonster.automation.element.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tastymonster.automation.base.WebDriverContext;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

/**
 * This is how we keep the Selenium interface at arms' length--this allows us to bend the hierarchy to our will
 * as applicable to our particular problem set. Mostly, this acts as a delegate, but also allows us to 
 * tailor the WebElements to our need, to keep end-users and test writers safe from Selenium's ever-changing
 * API, and to lay the groundwork for page transitions, tabs, etc that will make peoples' lives easier
 * @author mmerrell
 *
 */
public abstract class AbstractWebElement {

	protected String id;
	protected TabWebElement tab;
	protected AbstractAutomationPage page;
	
	public AbstractWebElement( String id, AbstractAutomationPage page ) {
		this( id, null, page );
	}

	public AbstractWebElement( String id, TabWebElement tab, AbstractAutomationPage page ) {
		super();
		this.id = id;
		this.tab = tab;
		this.page = page;
	}
	
	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public TabWebElement getTab() {
		return tab;
	}

	public void setTab( TabWebElement tab ) {
		this.tab = tab;
	}

	public AbstractAutomationPage getPage() {
		return page;
	}

	public void setPage( AbstractAutomationPage page ) {
		this.page = page;
	}
	
	public WebElement getWebElement() {
		return this.getWebElement( getId() );
	}

	protected WebElement getWebElement( String id ) {
		return this.getWebElement( id, getPage() );
	}

	private WebElement getWebElement( String id, AbstractAutomationPage page ) {
		return this.getWebElement( By.id( id ) );
	}

	private WebElement getWebElement( By by ) {
		return WebDriverContext.getDriver().findElement( by );
	}
}
