package com.tastymonster.automation.base;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the instance of the WebDriver the tester needs to interact with
 * @author mmerrell
 *
 */
public class WebDriverFacade implements IAutomationFacade {
    protected static final Logger log = LoggerFactory.getLogger(WebDriverFacade.class);
    
    private List<String> registeredWindowHandles = new LinkedList<String>();
	private final WebDriver driver;
    private AutomationWait automationWait;
	private final DriverType driverType;
	private String siteURL;
	
	public WebDriverFacade( WebDriver driver ) { 
		this.driver = driver;
		this.driverType = DriverType.valueOf( driver.getClass() );
		this.automationWait = new AutomationWait( this );
	}
	
	/**
	 * Navigates to the given URL
	 * @param url 
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
	 * @param by 
	 * @return 
	 */
	public WebElement findElement( By by ) {
		return getDriver().findElement( by );
	}

	public void setSiteRoot( String siteURL ) {
		this.siteURL = siteURL;
	}
	
	/**
	 * Returns the Root site, to which all URLs will be appended
	 * @return 
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

	public TargetLocator switchTo() {
	    return driver.switchTo();
	}

	/**
     * Returns the registered windows
     * @return
     */
    public List<String> getRegisteredWindows() {
        return registeredWindowHandles;
    }

    /**
     * Returns the AutomationWait instance associated with this facade
     * @return
     */
    public AutomationWait getWait() {
        return automationWait;
    }

	/**
	 * Synchronizes the registered windows with the currently open windows.  Handles closed and new windows (popups).
	 */
    @Override
    public void synchronizeWindows() {
        AutomationWait wait = getWait().setCustomTimeout(60L);
        wait.waitForCustomCondition(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                Set<String> currentWindows = driver.getWindowHandles();
                sleep(200);
                //After the specified interval, re-check the number of total window handles. If it is different from the 
                // "registered" number of windows we knew about before, then we can stop waiting
                return (currentWindows != null && currentWindows.size() != registeredWindowHandles.size());
            }

            @Override
            public String toString() {
                //If there was no change (after the max timeout set above) to the number of registered window handles, 
                // log the message and return to the calling context
                return String.format("Window change expected. %d registered windows", registeredWindowHandles.size());
            }
        });
        //Once we verify the number of windows have changed, wait a little while, then ask WebDriver for the current number of handles
        sleep(2000);

        //Remove all previous window handles 
        registeredWindowHandles.clear();
        Iterator<String> iterator = driver.getWindowHandles().iterator();
        while(iterator.hasNext()) {
            registeredWindowHandles.add(iterator.next());
        }
        // Now make sure and switch to the last window to be opened
        this.switchToWindow(registeredWindowHandles.get( registeredWindowHandles.size() - 1 ) );
    }
    
    WebDriver getBaseWebDriver() {
        return driver;
    }
    
    public static void sleep( long millis ) {
        try {
            Thread.sleep( millis );
        } catch ( InterruptedException e ) {
            log.error( "Thread.sleep() encountered an error: %s", e.getMessage() );
        }
    }

    /**
     * Switches to the specified window by index (e.g. 0 switches to the base (bottom) window)
     * @param index
     */
    public void switchToWindow(int index) {
        String window = registeredWindowHandles.get(index);
        this.switchToWindow(window);
    }

    /**
     * Switches to the specified window
     * @param windowHandle
     */
    public void switchToWindow(String windowHandle) {
        this.driver.switchTo().window(windowHandle);
    }

    /**
     * Returns a list of all the WebElements on the page that match the By locator
     * @param by
     * @return
     */
    @Override
    public List<WebElement> findElements( By by ) {
        return this.driver.findElements( by );
    }

    /**
     * Return the Current URL the browser is looking at
     * @return
     */
    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }
}
