package com.tastymonster.automation.element.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tastymonster.automation.base.DriverType;
import com.tastymonster.automation.base.IAutomationFacade;

public class MockWebDriverFacade implements IAutomationFacade {

	private WebElement element;
	
	@Override
	public WebElement findElement( By by ) {
		return element;
	}

	@Override
	public void get( String url ) {
	}

	@Override
	public String getSiteRoot() {
		return "";
	}

	@Override
	public DriverType getDriverType() {
		return DriverType.Firefox;
	}

	public void setElementToFind( WebElement element ) {
		this.element = element;
	}

	@Override
	public void setSiteRoot(String string) {
	}
}
