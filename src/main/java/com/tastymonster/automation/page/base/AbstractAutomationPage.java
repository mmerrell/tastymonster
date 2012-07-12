package com.tastymonster.automation.page.base;

import com.tastymonster.automation.base.IAutomationFacade;
import com.tastymonster.automation.base.WebDriverContext;

public abstract class AbstractAutomationPage implements IAutomationPage {

	public void navigate() {
		getDriver().get( getPageURL() );
	}
	
	public String getPageURL() {
		return getSiteRoot() + getPageURI();
	}
	
	protected IAutomationFacade getDriver() {
		return WebDriverContext.getDriver();
	}
	
	protected String getSiteRoot() {
		return getDriver().getSiteRoot();
	}
}
