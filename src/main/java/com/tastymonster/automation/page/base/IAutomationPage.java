package com.tastymonster.automation.page.base;

public interface IAutomationPage {

    /**
     * Returns the page's URL
     * 
     * @return
     */
    String getPageURI();

    String getPageSiteRoot();

    /**
     * Navigates to this page. Loads the page's URL by default. Override to
     * implement different behavior
     */
    void navigate();
}
