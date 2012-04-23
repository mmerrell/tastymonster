package com.tastymonster.automation.codegen;

import java.io.File;
import java.util.Set;

import junit.framework.TestCase;

public class TestPresentationType extends TestCase{

	//Pass in a velocity file extension and make sure it returns the correct implementation
	public void testGetParseVelocity() {
		File file = new File( "examplePath/page.vm" );
		PresentationType type = new PresentationType( file );
		type.determineParsingClass();
		
		IPresentationParser parser = type.getParser();
		assertEquals( "The Parser we got back should have been a ParseVelocity", parser.getClass().getSimpleName(), ParseVelocity.class.getSimpleName() );
	}
	
	//Pass in a freemarker file extension and make sure it returns the correct implementation
	public void testGetParseFreeMarker() {
		File file = new File( "examplePath/page.fm" );
		PresentationType type = new PresentationType( file );
		type.determineParsingClass();
		
		IPresentationParser parser = type.getParser();
		assertEquals( "The Parser we got back should have been a ParseFreeMarker", parser.getClass().getSimpleName(), ParseFreeMarker.class.getSimpleName() );
	}

	//Make sure it throws the proper exception if you give it a file extension that's not in the map
	public void testGetNonExistantParser() {
		File file = new File( "examplePath/page.doesnotexist" );
		PresentationType type = new PresentationType( file );
		
		try {
			type.determineParsingClass();
			fail( "This should have thrown a RuntimeException" );
		}
		catch ( RuntimeException ex ) {
			assertTrue( "The exception should contain a reference to the page name", ex.toString().contains( "page.doesnotexist" ) );
		}
	}

	//Test the ability to add a new parser to the PresentationTypes Map, then ensure it can be retrieved successfully when you throw a new file type at it
	public void testAddNewParser() {
		PresentationType.presentationTypeMap.put( ".file", ParseFictionalUI.class );
		PresentationType type = new PresentationType( new File( "examplePath/example.file" ) );
		type.determineParsingClass();
		
		assertEquals( "The Parsing Class should be the 'ParseFictionalUI' class", type.getParsingClass().getSimpleName(), ParseFictionalUI.class.getSimpleName() );
	}

	//Test the ability to add a new parser to the PresentationTypes Map, then ensure it can be retrieved successfully when you throw a new file type at it
	public void testChangeReferenceToExistingParser() {
		//Re-route the .vm extension to point to our favorite ParseFictionalUI parser
		PresentationType.presentationTypeMap.put( ".vm", ParseFictionalUI.class );
		PresentationType type = new PresentationType( new File( "examplePath/example.vm" ) );
		type.determineParsingClass();

		assertEquals( "The Parsing Class should be the 'ParseFictionalUI' class after we changed the definition for '.vm'", type.getParsingClass().getSimpleName(), ParseFictionalUI.class.getSimpleName() );
	}

	// This is just a nothing class for testing, so I didn't pretty up the code at all
	public class ParseFictionalUI implements IPresentationParser {
		@Override
		public Set<FieldDetails> buildFieldDetails() { return null; }
		@Override
		public String getPageName() { return null; }
		@Override
		public String getPageURI() { return null; }
		@Override
		public void initPageContents() {}
	}
}
