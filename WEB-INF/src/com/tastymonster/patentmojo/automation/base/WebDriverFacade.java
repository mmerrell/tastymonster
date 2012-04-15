package com.tastymonster.patentmojo.automation.base;

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

	/**
	 * Returns the Root site, to which all URLs will be appended
	 */
	@Override
	public String getSiteRoot() {
		return "http://localhost:8080/PatentMojo/";
	}
}
