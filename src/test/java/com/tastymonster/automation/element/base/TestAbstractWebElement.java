package com.tastymonster.automation.element.base;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.tastymonster.automation.base.IAutomationFacade;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

public class TestAbstractWebElement {

    @Test
	public void testAbstractWebElement() {
		AbstractWebElement element = new TestableAbstractWebElement( "fake" );
		
		Assert.assertEquals( "The element id should be 'fake", "fake", element.getId() );
		Assert.assertEquals( "The initial URL should be http://www.fake.com/fakepage", "http://www.fake.com/fakepage", element.getPage().getPageURL() );
		Assert.assertEquals( "The tab should be null by default", null, element.getTab());
		
		MockPage newPage = new MockPage();
		newPage.setPageURI( "/newfakepage" );
		
		TabWebElement newTab = new TabWebElement( "newTab", newPage );
		
		element.setId( "new fake" );
		element.setPage( newPage );
		element.setTab( newTab );

		Assert.assertEquals( "The new id should be 'new fake', but instead it's " + element.getId(), "new fake", element.getId() );
		Assert.assertEquals( "The new tab id should be 'newTab', but instead it's " + element.getTab().getId(), "newTab", element.getTab().getId() );
		Assert.assertEquals( "The new page URL should be 'http://www.fake.com/newfakepage', but instead it's " + element.getPage().getPageURL(), "http://www.fake.com/newfakepage", element.getPage().getPageURL() );
	}
	
    @Test
	public void testByLocator() {
        AbstractWebElement element = new TestableAbstractWebElement( "fake" );
        Assert.assertEquals( By.id("fake").toString(), element.getBy().toString() );
        Assert.assertEquals( "fake", element.getId() );
    }
	
	public class TestableAbstractWebElement extends AbstractWebElement {
		public TestableAbstractWebElement( String id ) {
			super( id, new MockPage() );
		}
		
        public TestableAbstractWebElement( String id, AbstractAutomationPage page ) {
            super( id, page );
        }
        
		@Override
		protected IAutomationFacade getDriver() {
			MockWebDriverFacade mockDriver = new MockWebDriverFacade();
			mockDriver.setElementToFind( new MockWebElement() );
			return mockDriver;
		}
	}
}
