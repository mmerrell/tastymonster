package com.tastymonster.automation.element.base;

import junit.framework.Assert;

import org.testng.annotations.Test;

import com.tastymonster.automation.base.IAutomationFacade;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

@Test
public class TestAbstractWebElement {

	public void testAbstractWebElement() {
		AbstractAutomationPage page = new AbstractAutomationPage() {
			@Override
			public String getPageURI() {
				return "http://www.fake.com";
			}
		};

		AbstractWebElement element = new TestableAbstractWebElement( "fake", page );
		
		Assert.assertEquals( "The element id should be 'fake", "fake", element.getId() );
		Assert.assertEquals( "The initial URL should be http://www.fake.com", "http://www.fake.com", page.getPageURL() );
		Assert.assertEquals( "The tab should be null by default", null, element.getTab());
		
		AbstractAutomationPage newPage = new AbstractAutomationPage() {
			@Override
			public String getPageURI() {
				return "http://www.newfake.com";
			}
		};
		TabWebElement newTab = new TabWebElement( "newTab", page );
		
		element.setId( "new fake" );
		element.setPage( newPage );
		element.setTab( newTab );

		Assert.assertEquals( "The new id should be 'new fake', but instead it's " + element.getId(), "new fake", element.getId() );
		Assert.assertEquals( "The new tab id should be 'newTab', but instead it's " + element.getTab().getId(), "newTab", element.getTab().getId() );
		Assert.assertEquals( "The new page URL should be 'http://www.newfake.com', but instead it's " + element.getPage().getPageURL(), "http://www.newfake.com", element.getPage().getPageURL() );
	}
	
	public class TestableAbstractWebElement extends AbstractWebElement {

		public TestableAbstractWebElement( String id, AbstractAutomationPage page ) {
			super( id, page );
		}
		
		@Override
		protected IAutomationFacade getDriver() {
			MockWebDriverFacade mockDriver = new MockWebDriverFacade();
			mockDriver.setElementToFind( new MockWebElement() );
			return new MockWebDriverFacade();
		}
	}
}
