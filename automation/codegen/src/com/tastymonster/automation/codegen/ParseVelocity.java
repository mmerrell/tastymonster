package com.tastymonster.automation.codegen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.tastymonster.automation.element.base.ButtonWebElement;
import com.tastymonster.automation.element.base.DivWebElement;
import com.tastymonster.automation.element.base.LinkWebElement;
import com.tastymonster.automation.element.base.PlaceholderWebElement;
import com.tastymonster.automation.element.base.TableWebElement;
import com.tastymonster.automation.element.base.TextBoxWebElement;

public class ParseVelocity implements IPresentationParser {

	private static Pattern velocityMacroPattern = Pattern.compile( "\\s*#(\\w+)\\s*\\(\\s*(.*)\\s*\\)" );

	protected File file;
	protected Set<FieldDetails> fields;
	
	/**
	 * The contents of the page in one big String
	 */
	private String pageContents;
	
	/**
	 * The name of the page for code generation purposes (i.e. without file extension, etc)
	 */
	private String pageName;
	
	/**
	 * How the browser reaches the page
	 */
	private String pageURI;

	
	public ParseVelocity( File file ) {
		this.file = file;
	}
	
	public Set<FieldDetails> getFields() {
		return fields;
	}
	
	public String getPageContents() {
		return pageContents;
	}

	@Override
	public String getPageName() {
		return pageName;
	}

	public void setPageName( String pageName ) {
		this.pageName = pageName;
	}

	private void setPageURI( String pageURI ) {
		this.pageURI = pageURI;
	}

	@Override
	public String getPageURI() {
		return pageURI;
	}

	/**
	 * Sets the member variable pageContents by reading the file contents
	 * @param path
	 */
	public void setPageContents( String pageContents ) {
		this.pageContents = pageContents;
	}
	
	//Maps velocity field types (macros) to WebElement types
	private static Map<String, WebElementDetails> macroNameToWebElement = new HashMap<String, WebElementDetails>();
	
	static {
		//Forms
		macroNameToWebElement.put( "formInput", new WebElementDetails( TextBoxWebElement.class ) );
		macroNameToWebElement.put( "formPassword", new WebElementDetails( TextBoxWebElement.class ) );
		macroNameToWebElement.put( "formInputWithType", new WebElementDetails( TextBoxWebElement.class ) );
		macroNameToWebElement.put( "formButton", new WebElementDetails( ButtonWebElement.class ) );

		//Tables
		macroNameToWebElement.put( "table", new WebElementDetails( TableWebElement.class ) );
		macroNameToWebElement.put( "table2D", new WebElementDetails( TableWebElement.class ) );
		
		//Positional elements
		macroNameToWebElement.put( "divStart", new WebElementDetails( DivWebElement.class ) );
		macroNameToWebElement.put( "divStartBox", new WebElementDetails( DivWebElement.class ) );
		macroNameToWebElement.put( "divStartWithClass", new WebElementDetails( DivWebElement.class ) );
		
		//Clickables
		macroNameToWebElement.put( "link", new WebElementDetails( LinkWebElement.class ) );
		macroNameToWebElement.put( "button", new WebElementDetails( ButtonWebElement.class ) );
	}
	
	//Maps velocity field types (macros) to WebElement types
	private static List<String> macrosToIgnore = new ArrayList<String>();
	
	static {
		macrosToIgnore.add( "if" );
		macrosToIgnore.add( "parse" );
		macrosToIgnore.add( "clearfix" );
		macrosToIgnore.add( "divEnd" );
		macrosToIgnore.add( "horizontalTabBar" );
		macrosToIgnore.add( "formEnd" );
		macrosToIgnore.add( "tablerows" );
		macrosToIgnore.add( "set" );
	}
	
	@Override
	public Set<FieldDetails> buildFieldDetails() {
		Set<FieldDetails> fields = new HashSet<FieldDetails>();
		
		//Here is where the rubber meets the road
		// Compare the page contents with a facility for mapping the Velocity macros to a set of regex Patterns
		// What gets returned is a fully realized WebElementDetails object, strongly typed to the corresponding 
		// WebElement type, and containing its page name, its Tab ID, and other details
		for ( String line: StringUtils.split( pageContents, System.getProperty("line.separator") ) ) {
			Matcher m = parseOneLine( StringUtils.trim( line ) );
			if ( m.matches() ) {
				String macroName = m.group( 1 );
				String macroParams = m.group( 2 );
				WebElementDetails elementDetails = getElementDetails( macroName );
				
				//If elementDetails can't resolve to a well-formed set of FieldDetails, just move to the next
				if ( elementDetails == null ) {
					continue;
				}
				
				Map<String, String> fieldAttributes = getFieldAttributesFromParameters( macroParams );
				fields.add( new FieldDetails( fieldAttributes, elementDetails.getType(), macroName ) );
			}
		}
		
		return fields;
		
	}

	/**
	 * Split up the velocity macro parameters and put them into a map. The map should contain an "id" at the very least
	 * @param m
	 * @return
	 */
	private Map<String, String> getFieldAttributesFromParameters( String macroParams ) {
		Map<String, String> fieldAttributes = new HashMap<String, String>();
		List<String> paramList = Arrays.asList( macroParams.split( "\\s+" ) );
		List<String> scrubbedParams = scrubParams( paramList );
		
		//The first parameter will be the id
		if ( scrubbedParams.size() >=1 ) {
			fieldAttributes.put( "id", scrubbedParams.get( 0 ) );
		}
		
		//If the macro contains a third parameter, it's the field label
		if ( scrubbedParams.size() >= 4 ) {
			String normalizedFieldName = AutomationUtils.normalizeFieldName( scrubbedParams.get( 3 ) );
			fieldAttributes.put( "fieldName", normalizedFieldName );
		} else {
			fieldAttributes.put( "fieldName", scrubbedParams.get( 0 ) );
		}
		
		return fieldAttributes;
	}

	private List<String> scrubParams( List<String> paramList ) {
		List<String> scrubbedList = new ArrayList<String>();
		for ( String string: paramList ) {
			scrubbedList.add( AutomationUtils.normalizeFieldName( string ) );
		}
		return scrubbedList;
	}

	/**
	 * Returns a WebElementDetails object mapped to the correct type for this element. If the element is one to be ignored, 
	 * this will return null, so you must check for that 
	 * @param macroName
	 * @return
	 */
	protected WebElementDetails getElementDetails( String macroName ) {
		if ( macrosToIgnore.contains( macroName ) ) {
			//Check this null!
			return null;
		}
		if ( macroNameToWebElement.containsKey( macroName ) ) {
			return macroNameToWebElement.get( macroName );
		}
		return new WebElementDetails( PlaceholderWebElement.class );
	}

	/**
	 * This parses a line and returns the appropriate matcher, but it does NOT initialize the match itself!
	 * You must call matches() on the return value in order to parse the line 
	 * @param line
	 * @return
	 */
	protected Matcher parseOneLine( String line ) {
		Matcher matcher = velocityMacroPattern.matcher( line );
		return matcher;
	}
	
	/**
	 * Read and return the contents of the file. This should only be called from the ParserFactory
	 * @param path
	 */
	public void initPageContents() {
		try {
			//Use the setter for pageContents
			this.setPageContents( readFile( file.getPath() ) );
			this.setPageURI( file.getName() );
			this.setPageName( file.getName().replace( ".vm", "" ) );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
	
/**
	 * This should be in a Utils class
	 * @param path
	 * @return
	 * @throws IOException
	 */
	protected String readFile(String path) throws IOException {
	    FileInputStream stream = new FileInputStream(new File(path));
	    try {
	      FileChannel fc = stream.getChannel();
 	      MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
	      // Instead of using default, pass in a decoder
	      return Charset.defaultCharset().decode(bb).toString();
	    }
	    finally {
	      stream.close();
	    }
	}
}
