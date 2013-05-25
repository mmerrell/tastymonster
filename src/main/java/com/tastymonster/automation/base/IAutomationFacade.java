package com.tastymonster.automation.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * We want to limit the tester's need to interact with the WebDriver module, so this class acts as a sort of delegate
 * to the real thing. It also imposes some limitations on the tester's reach, allowing us to keep up with changes to the 
 * real WebDriver implementations and browser differences, and allow the tester to worry only about the tests
 * @author mmerrell
 *
 */
public interface IAutomationFacade {

	/**
	 * Find an element on the page
	 * @param by
	 * @return
	 */
	WebElement findElement( By by );

    /**
     * Find an element on the page
     * @param by
     * @return
     */
    List<WebElement> findElements( By by );

	/**
	 * Navigate to the given URL
	 * @param url
	 */
	void get( String url );

	/**
	 * Get the Site Root for the initial context to this test
	 * @return
	 */
	String getSiteRoot();

	public abstract DriverType getDriverType();

	/**
	 * Set the Site Root for the initial end-point to open
	 * @param string
	 */
	void setSiteRoot(String string);

    /**
     * Synchronizes the registered windows with the currently open windows.  Handles closed and new windows (popups).
     */
    void synchronizeWindows();

    /**
     * Returns the current URL the browser is sitting on
     * @return
     */
    String getCurrentUrl();
}
