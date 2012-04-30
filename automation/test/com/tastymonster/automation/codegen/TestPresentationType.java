package com.tastymonster.automation.codegen;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class TestPresentationType {

	@Test( groups = { "runFirst" } )
	//Pass in a velocity file extension and make sure it returns the correct implementation
	public void testGetParseVelocity() {
		File file = new File( "examplePath/page.vm" );
		PresentationType type = new PresentationType( file );
		type.determineParsingClass();
		
		IPresentationParser parser = type.getParser();
		Assert.assertEquals( parser.getClass().getSimpleName(), ParseVelocity.class.getSimpleName(), "The Parser we got back should have been a ParseVelocity" );
	}
	
	//Pass in a freemarker file extension and make sure it returns the correct implementation
	public void testGetParseFreeMarker() {
		File file = new File( "examplePath/page.fm" );
		PresentationType type = new PresentationType( file );
		type.determineParsingClass();
		
		IPresentationParser parser = type.getParser();
		Assert.assertEquals(parser.getClass().getSimpleName(), ParseFreeMarker.class.getSimpleName(),  "The Parser we got back should have been a ParseFreeMarker" );
	}

	//Make sure it throws the proper exception if you give it a file extension that's not in the map
	public void testGetNonExistentParser() {
		File file = new File( "examplePath/page.doesnotexist" );
		PresentationType type = new PresentationType( file );
		
		try {
			type.determineParsingClass();
			Assert.fail( "This should have thrown a RuntimeException" );
		}
		catch ( RuntimeException ex ) {
			Assert.assertTrue( ex.toString().contains( "page.doesnotexist" ),  "The exception should contain a reference to the page name" );
		}
	}

	//Test the ability to add a new parser to the PresentationTypes Map, then ensure it can be retrieved successfully when you throw a new file type at it
	public void testAddNewParser() {
		PresentationType.addPresentationType( ".file", ParseTestStub.class );
		PresentationType type = new PresentationType( new File( "examplePath/example.file" ) );
		type.determineParsingClass();
		
		Assert.assertEquals( type.getParsingClass().getSimpleName(), ParseTestStub.class.getSimpleName(), "The Parsing Class should be the 'ParseFictionalUI' class"  );
	}

	//This test needs to be run after the "happy path" ParseVelocity test. Since TestNG preserves state from one test to another, the static map will be changed by this test.
	// Therefore it needs to be run after the other one
	@Test( dependsOnGroups = { "runFirst" } )
	public void testChangeReferenceToExistingParser() {
		//Re-route the .vm extension to point to our favorite ParseFictionalUI parser
		PresentationType.addPresentationType( ".vm", ParseTestStub.class );
		PresentationType type = new PresentationType( new File( "examplePath/example.vm" ) );
		type.determineParsingClass();

		Assert.assertEquals( type.getParsingClass().getSimpleName(), ParseTestStub.class.getSimpleName(), "The Parsing Class should be the 'ParseFictionalUI' class after we changed the definition for '.vm'" );
	}
}
