package com.tastymonster.automation.element.base;

public interface IBaseWebElement {

    boolean isDisplayed();
    
    String getAttribute( String attrName );
}
