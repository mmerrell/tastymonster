package com.tastymonster.automation.base;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Some methods to supplement those available via WebDriver's built-in wait mechanism
 */
public class AutomationWait {
    private final IAutomationFacade driver;
    private final Long TIMEOUT_IN_SECONDS = 15L;
    
    private Long customTimeout = null;
    private boolean useCustomTimeoutIndefinitely = false;
    private Map<Long, WebDriverWait> waits = new HashMap<Long, WebDriverWait>();
    private static final Logger log = LoggerFactory.getLogger( AutomationWait.class );
    
    public AutomationWait( IAutomationFacade driver ) {
        this.driver = driver;
    }

    /**
     * Sets a custom timeout that will work for one wait action
     * @param timeoutInSeconds
     * @return 
     */
    public AutomationWait setCustomTimeout( Long timeoutInSeconds ) {
        this.setCustomTimeout( timeoutInSeconds, false );
        return this;
    }

    /**
     * Sets the custom timeout. If @param useCustomTimeoutIndefinitely is true, this value will be used indefinitely,
     * otherwise it will be used for one wait only
     * @param timeoutInSeconds
     * @param useCustomTimeoutIndefinitely
     */
    public void setCustomTimeout( Long timeoutInSeconds, boolean useCustomTimeoutIndefinitely ) {
        this.customTimeout = timeoutInSeconds;
        this.useCustomTimeoutIndefinitely = useCustomTimeoutIndefinitely;
    }

    /**
     * Returns the timeout in seconds.
     * @return
     */
    private Long getTimeoutInSeconds() {
        Long returnTimeout;
        // If useCustomTimeoutIndefinitely is set to true, use our custom timeout value.
        if( useCustomTimeoutIndefinitely ) {
            // Throw an exception if this is null as this should be not be configured to use this way
            if( null == customTimeout ) {
                throw new AutomationWaitException( "Custom timeout was null when set to use custom timeouts" );
            }
            returnTimeout = customTimeout;
        } else {
            // If we have a custom timeout and useCustomTimeoutIndefinitely is set to false use it
            if( null != customTimeout ) {
                returnTimeout = customTimeout;
                // Remember to set it to null so we don't attempt to use it again.
                customTimeout = null;
            } else {
                // If we get here, then use our default timeout
                returnTimeout = TIMEOUT_IN_SECONDS;
            }
        }
        return returnTimeout;
    }

    /**
     * Waits for the specified condition to complete.  The T value returned must not be null, or if Boolean, must not be not
     * null or false for the wait to complete.  If the wait condition is not satisfied in the configured amount of time,
     * an exception will be thrown
     * @param expectedCondition
     * @return 
     */
    public <T> T waitForCustomCondition( ExpectedCondition<T> expectedCondition ) {
        return getDefaultWait().until( expectedCondition );
    }

    /**
     * Returns a new WebDriver wait with the default timeout
     * @return
     */
    private WebDriverWait getDefaultWait() {
        Long timeOutToUse = getTimeoutInSeconds();
        if( !waits.containsKey( timeOutToUse ) ) {
            waits.put( timeOutToUse, new WebDriverWait( ( ( WebDriverFacade ) driver ).getBaseWebDriver(), timeOutToUse ) );
        }
        return waits.get( timeOutToUse );
    }

    /**
     * Wait for an element (identified by the param)
     * @param by 
     * @return 
     * @throws Exception
     */
    public WebElement waitForElementPresent( final By by ) {
        return getDefaultWait().until( ExpectedConditions.presenceOfElementLocated( by ) );
    }

    /**
     * Causes the test to wait for a specific object to become visible on the page. An exception will be thrown once the maximum wait time has been exceeded.  
     * @param by 
     * @return 
     * @throws Exception 
     */
    public WebElement waitForElementVisible( final By by ) {
        return getDefaultWait().until( ExpectedConditions.visibilityOfElementLocated( by ) );
    }

    /**
     * Causes the test to wait for a specific object to be present and visible on the page. An exception will be thrown if the maximum wait time has been exceeded.  
     * @param by 
     * @return 
     * @throws Exception 
     */
    public WebElement waitForElementDisplayed( By by ) {
        waitForElementPresent( by );
        return waitForElementVisible( by );
    }

    public static void sleep( long millis ) {
        try {
            Thread.sleep( millis );
        } catch ( InterruptedException e ) {
            log.error( String.format( "Thread.sleep() encountered an error: %s", e.getMessage() ) );
        }
    }
}

class AutomationWaitException extends RuntimeException {
    private static final long serialVersionUID = 5074263318005328470L;

    public AutomationWaitException( String message ) {
        super( message );
    }
}
