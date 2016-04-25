package com.tastymonster.automation.element.base;

import com.tastymonster.automation.base.IAutomationFacade;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

public class MockPage extends AbstractAutomationPage {

    private String pageURI = "/fakepage";

    @Override
    public String getPageURI() {
        return pageURI;
    }

    @Override
    protected IAutomationFacade getDriver() {
        return new MockWebDriverFacade();
    };

    public void setPageURI(String pageURI) {
        this.pageURI = pageURI;
    }

    @Override
    public String getPageSiteRoot() {
        // TODO Auto-generated method stub
        return "http://localhost:8080";
    }
}
