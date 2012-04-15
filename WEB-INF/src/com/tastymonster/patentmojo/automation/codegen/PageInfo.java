package com.tastymonster.patentmojo.automation.codegen;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Each PageInfo object represents a single "page", as defined by the caller. If a page represents more than one template, it will be  
 * a combination of these PageInfo objects meshed together
 * @author Marcus
 *
 */
public class PageInfo {

	//This is only protected for unit testing purposes
	protected Set<FieldDetails> fields = new HashSet<FieldDetails>();
	protected String pageName;
	protected String pageURI;
	protected PresentationType presentationType;
	
	public Set<FieldDetails> getFields() {
		return fields;
	}

	public void setFields( Set<FieldDetails> fields ) {
		this.fields = fields;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPackage() {
		return "com.tastymonster.patentmojo.automation.page.base";
	}

	public void setPageURI( String pageURI ) {
		this.pageURI = pageURI;
	}

	public String getPageURI() {
		return pageURI;
	}

	public PresentationType getPresentationType() {
		return presentationType;
	}

	public void setPresentationType(PresentationType presentationType) {
		this.presentationType = presentationType;
	}

	/**
	 * The file will be parsed according to its implementation. It will call parseVelocity, parseJSP, or 
	 * whatever type (plug-in) is appropriate
	 * @param file
	 * @return
	 */
	public void initPage( File file ) {
		Set<FieldDetails> fields = new HashSet<FieldDetails>();
		
		//Get parser from factory by sending it the file
		//The factory will return an IPresentationParser with the correct implementation
		// and its file contents initialized
		IPresentationParser parser = ParserFactory.getParser( file );

		this.setPageName( parser.getPageName() );
		this.setPageURI( parser.getPageURI() );
		fields = parser.buildFieldDetails();
		
		//Call the methods on the parser to populate the correct field details
		this.setFields( fields );
	}
}
