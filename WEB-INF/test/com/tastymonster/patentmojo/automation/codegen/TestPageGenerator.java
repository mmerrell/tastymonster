package com.tastymonster.patentmojo.automation.codegen;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPageGenerator {

	private static final String pathToTest = "WEB-INF\\test\\com\\tastymonster\\patentmojo\\automation\\codegen\\";

	@Test( groups = { "PageGenerator" } )
	public void testMergeVMContext() throws IOException {
		PageGenerator generator = new PageGenerator();
		Map<String, Object> context = new LinkedHashMap<String, Object>();
		context.put( "test", "This is a String" );
		
		String templatePath = new File( pathToTest + "testGenerateText.vm" ).getPath();
		StringWriter testWriter = new StringWriter();
        generator.mergeVMTemplate( context, templatePath, testWriter );
        Assert.assertEquals( testWriter.toString(), "This is a String", "Expected the merged text to contain the correct string" );
	}
	
	@Test( groups = { "integration" } )
	public void testGeneratePages() {
		PageGenerator generator = new PageGenerator();
		generator.generatePages();
	}
}
