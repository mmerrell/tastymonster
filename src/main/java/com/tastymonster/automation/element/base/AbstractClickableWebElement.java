package com.tastymonster.automation.element.base;

import org.openqa.selenium.By;

import com.tastymonster.automation.base.WebDriverContext;
import com.tastymonster.automation.element.base.AbstractWebElement;
import com.tastymonster.automation.element.base.IClickable;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

/**
 * All clickable elements will click() the same way
 * @author mmerrell
 *
 */
public abstract class AbstractClickableWebElement extends AbstractWebElement implements IClickable {

	public AbstractClickableWebElement(String id, AbstractAutomationPage page) {
		super( id, page );
	}

	public void click() {
		WebDriverContext.getDriver().findElement( By.id( this.getId() ) ).click();
	}
}
