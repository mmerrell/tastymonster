package com.tastymonster.automation.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This class implements the instance of the WebDriver the tester needs to interact with
 * @author mmerrell
 *
 */
public class WebDriverFacade implements IAutomationFacade {

	private final WebDriver driver;
	private final DriverType driverType;
	private String siteURL;
	
	public WebDriverFacade( WebDriver driver ) { 
		this.driver = driver;
		this.driverType = DriverType.valueOf( driver.getClass() );
	}
	
	/**
	 * Navigates to the given URL
	 */
	public void get( String url ) {
		driver.get( url );
	}

	/**
	 * This is just for unit testing--override this if you want to return a MockWebDriver instead
	 * @return
	 */
	protected WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * Locates the given element on the page, and returns the actual Selenium WebElement implementation of it
	 */
	public WebElement findElement( By by ) {
		return getDriver().findElement( by );
	}

	public void setSiteRoot( String siteURL ) {
		this.siteURL = siteURL;
	}
	
	/**
	 * Returns the Root site, to which all URLs will be appended
	 */
	public String getSiteRoot() {
		return siteURL;
	}

	/**
	 * Returns the type of driver being implemented right now
	 * @return
	 */
	public DriverType getDriverType() {
		return driverType;
	}
}
