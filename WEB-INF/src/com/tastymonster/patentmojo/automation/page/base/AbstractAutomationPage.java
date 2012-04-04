package com.tastymonster.patentmojo.automation.page.base;

import com.tastymonster.patentmojo.automation.base.IAutomationFacade;
import com.tastymonster.patentmojo.automation.base.WebDriverContext;

public abstract class AbstractAutomationPage implements IAutomationPage {

	@Override
	public abstract String getPageURI();

	@Override
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
