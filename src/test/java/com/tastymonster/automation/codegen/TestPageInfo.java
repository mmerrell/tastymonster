package com.tastymonster.automation.codegen;

import java.io.File;

import junit.framework.Assert;

import org.testng.annotations.Test;

@Test
public class TestPageInfo {

	public void testInitPages() {
		PresentationType.addPresentationType( ".test", ParseTestStub.class );
		PageInfo page = new PageInfo();
		page.initPage( new File( "Something.test" ) );
		
		Assert.assertEquals( "The package should be 'com.tastymonster.automation.page.base'", "com.tastymonster.automation.page.base", page.getPackage() );
		Assert.assertEquals( "The Page URI should be 'TestPage.test'", "TestPage.test", page.getPageURI() );
		Assert.assertEquals( "The PageName should be 'TestPage.test'", "TestPage.test", page.getPageName() );
		Assert.assertEquals( "The id of the Field should be 'test'", "test", page.getFields().iterator().next().getId() );
	}
}
