package com.tastymonster.patentmojo.automation.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IAutomationFacade {

	WebElement findElement( By by );

	void get( String url );

	String getSiteRoot();

}
