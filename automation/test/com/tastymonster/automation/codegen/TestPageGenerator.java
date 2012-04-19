package com.tastymonster.automation.codegen;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tastymonster.automation.codegen.PageGenerator;


public class TestPageGenerator {

	//TODO hard-coding for demo
	private static final String pathToTest = "automation/test/com/tastymonster/automation/codegen/";

	@Test( groups = { "PageGenerator" } )
	public void testMergeVMContext() throws IOException {
		//Create the context for the velocity macros
		PageGenerator generator = new PageGenerator();
		Map<String, Object> context = new LinkedHashMap<String, Object>();
		context.put( "test", "This is a String" );
		
		//merge the template with the Map above 
		String templatePath = new File( pathToTest + "testGenerateText.vm" ).getPath();
		StringWriter testWriter = new StringWriter();
        try {
			generator.mergeVMTemplate( context, templatePath, testWriter );
		} catch ( Exception e ) {
			e.printStackTrace();
			Assert.fail( "mergeVMTemplate threw an exception: " + e.toString() );
		}
		
		//Make sure the macros got interpolated correctly
        Assert.assertEquals( testWriter.toString(), "This is a String", "Expected the merged text to contain the correct string" );
	}
	
	@Test( groups = { "integration" } )
	public void testGeneratePages() {
		//Generate the pages
		PageGenerator generator = new PageGenerator();
		generator.generatePages();
	}
}
