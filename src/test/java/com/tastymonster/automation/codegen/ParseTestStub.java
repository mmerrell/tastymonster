package com.tastymonster.automation.codegen;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tastymonster.automation.element.base.TextBoxWebElement;

public class ParseTestStub implements IPresentationParser {

	public ParseTestStub( File file ) {
		//Do nothing
	}
	
	@Override
	public Set<FieldDetails> buildFieldDetails() {
		Set<FieldDetails> fields = new HashSet<FieldDetails>();
		
		WebElementDetails elementDetails = new WebElementDetails( TextBoxWebElement.class );
		Map<String, String> fieldAttributes = new HashMap<String, String>();
		fieldAttributes.put( "id", "test" );
		fields.add( new FieldDetails( fieldAttributes, elementDetails.getType(), "testMacro" ) );
		
		return fields;
	}

	@Override
	public String getPageName() {
		return "TestPage.test";
	}

	@Override
	public String getPageURI() {
		return "TestPage.test";
	}

	@Override
	public void initPageContents() {
	}

}
