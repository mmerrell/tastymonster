package com.tastymonster.patentmojo.automation.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverFacade implements IAutomationFacade {

	private final WebDriver driver;
	private final DriverType driverType;
	
	public WebDriverFacade( WebDriver driver ) {
		this.driver = driver;
		this.driverType = DriverType.valueOf( driver.getClass() );
	}
	
	public void get( String url ) {
		getDriver().get( url );
	}

	protected IAutomationFacade getDriver() {
		return WebDriverContext.getDriver();
	}
	
	public WebElement findElement( By by ) {
		return getDriver().findElement( by );
	}

	@Override
	public String getSiteRoot() {
		return "http://localhost:8080/PatentMojo/";
	}
}
