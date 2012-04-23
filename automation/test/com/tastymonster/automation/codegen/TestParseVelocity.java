package com.tastymonster.automation.codegen;

import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestParseVelocity {

	String pageContents;
	
	@Test( groups = { "ParserTests" } )
	public void testParseOneLine() {
		//You can initialize a ParseVelocity with null--it doesn't try to read the file unless you actually call initPageContents
		TestableParseVelocity testableParser = new TestableParseVelocity( null );
		Matcher m = testableParser.parseOneLine( "#formInput( \"loginField\" \"Login\" \"login.userId\" )" );
		m.matches();
		Assert.assertEquals( m.group( 1 ), "formInput", "The macro should be parsed as 'formInput'" );
		Assert.assertEquals( StringUtils.trim( m.group( 2 ) ), "\"loginField\" \"Login\" \"login.userId\"", "The parameters to the macro should be correct" );
	}
	
	@Test( groups = { "ParserTests" } )
	public void testGetElementDetailsFormInput() {
		TestableParseVelocity testableParser = new TestableParseVelocity( null );
		Matcher matcher = testableParser.parseOneLine( "#formInput( \"loginField\" \"Login\" \"login.userId\" )" );
		matcher.matches();
		WebElementDetails testElementDetails = testableParser.getElementDetails( matcher.group( 1 ) );
		Assert.assertEquals( testElementDetails.getType().getSimpleName(), "TextBoxWebElement", "The element should come back as a TextBoxWebElement" );
	}
	
	@Test( groups = { "ParserTests" } )
	public void testGetElementDetailsFormPassword() {
		TestableParseVelocity testableParser = new TestableParseVelocity( null );
		Matcher matcher = testableParser.parseOneLine( "#formPassword(  )" );
		matcher.matches();
		WebElementDetails testElementDetails = testableParser.getElementDetails( matcher.group( 1 ) );
		Assert.assertEquals( testElementDetails.getType().getSimpleName(), "TextBoxWebElement", "The element should come back as a TextBoxWebElement" );
	}
	
	@Test( groups = { "ParserTests" } )
	public void testGetElementDetailsTable() {
		TestableParseVelocity testableParser = new TestableParseVelocity( null );
		Matcher matcher = testableParser.parseOneLine( "#table(  )" );
		matcher.matches();
		WebElementDetails testElementDetails = testableParser.getElementDetails( matcher.group( 1 ) );
		Assert.assertEquals( testElementDetails.getType().getSimpleName(), "TableWebElement", "The element should come back as a TableWebElement" );
	}
	
	@Test( groups = { "ParserTests" } )
	public void testBuildFieldDetails() {
		TestableParseVelocity testableParser = new TestableParseVelocity( null );
		testableParser.setPageContents( 
			"#formInput( \"loginField\" \"Login\" \"login.userId\" )"
		);
		
		Set<FieldDetails> fields = testableParser.buildFieldDetails();
		Assert.assertEquals( 1, fields.size(), "fields should contain 1 elements" );
		Iterator<FieldDetails> it = fields.iterator();
		
		FieldDetails textBoxDetails = it.next();
		Assert.assertEquals( textBoxDetails.getId(), "loginField", "The TextBox element should have the id 'loginField'" );
	}
	
	public class TestableParseVelocity extends ParseVelocity {

		public TestableParseVelocity( File file ) {
			super( file );
		}
		
		@Override
		public Matcher parseOneLine(String line) {
			return super.parseOneLine(line);
		}
		
		@Override
		protected WebElementDetails getElementDetails( String elementType ) {
			return super.getElementDetails( elementType );
		}
	}
}
