package com.tastymonster.automation.element.base;

import org.openqa.selenium.By;

import com.tastymonster.automation.base.WebDriverContext;
import com.tastymonster.automation.element.base.AbstractWebElement;
import com.tastymonster.automation.element.base.IClickable;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

/**
 * All clickable elements will click() the same way. This can be overridden in descendent classes as needed.
 * If the element you're clicking on causes a popup window or new tab (or closes the same), call the popupExpected() method after
 * construction. This will cause WebDriver to relocate and switch to the "top-level" window currently available
 * @author mmerrell
 *
 */
public abstract class AbstractClickableWebElement extends AbstractWebElement implements IClickable {
    private boolean popupsExpected = false;

	public AbstractClickableWebElement(String id, AbstractAutomationPage page) {
		super( id, page );
	}

	public void click() {
	    WebDriverContext.getDriver().findElement( By.id( this.getId() ) ).click();
        if(popupsExpected) {
            getDriver().synchronizeWindows();
        }
	}

	/**
     * Call this method on any Clickable WebElement where a popup is expected (or removed) imediately after clicking
     */
    public void popupExpected() {
        popupsExpected = true;
    }
}
