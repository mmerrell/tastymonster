package com.tastymonster.automation.element.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tastymonster.automation.base.DriverType;
import com.tastymonster.automation.base.IAutomationFacade;

public class MockWebDriverFacade implements IAutomationFacade {
    private String siteRoot = "http://www.fake.com";  //Default starting url--can be changed with setSiteRoot()
    private String currentUrl = siteRoot;             //starts at siteRoot, but can be set by the get() method
    private WebElement elementToFind;                 //the element set to be returned by findElement()
    private List<WebElement> elementsToFind;          //the list of elements to be returned by findElements()
	
	@Override
	public WebElement findElement( By by ) {
		return elementToFind;
	}

	@Override
	public void get( String url ) {
	    currentUrl = url;
	}

	@Override
	public String getSiteRoot() {
		return siteRoot;
	}

	@Override
	public DriverType getDriverType() {
		return DriverType.Firefox;
	}

	public void setElementToFind( WebElement elementToFind ) {
		this.elementToFind = elementToFind;
	}

	@Override
	public void setSiteRoot( String siteRoot ) {
	    this.siteRoot = siteRoot;
	}

    @Override
    public List<WebElement> findElements( By by ) {
        return elementsToFind;
    }

    @Override
    public void synchronizeWindows() {
        //Do nothing for the moment--need to write unit tests
    }

    @Override
    public String getCurrentUrl() {
        return currentUrl;
    }

    //******************************************************
    // Methods for unit testing
    public void setElementsToFind( List<WebElement> elementsToFind ) {
        this.elementsToFind = elementsToFind;
    }
}
