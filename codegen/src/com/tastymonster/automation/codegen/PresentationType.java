package com.tastymonster.automation.codegen;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the different file template types. To use this, you'll have to extend the class, define your own presentationTypeMap, 
 * and possibly implement your own getParser method. It's possible we could make the map a <String, Method> instead of <String, Class>, 
 * which would specify the method you wish to call when you encounter a particular file extension. We may also want to find another
 * mechanism for mapping files to parser classes, because it might not always be a file with a discernible extension
 * @author mmerrell
 *
 */
public class PresentationType {
	
	/**
	 * The Parsing Class used by the given File type
	 */
	protected Class<? extends IPresentationParser> parsingClass;
	
	/**
	 * The actual File we're going to analyze
	 */
	private File file;

	/**
	 * This Map associates files of a given type with the Parsing Class that can put them into the correct context
	 */
	protected static Map<String, Class<? extends IPresentationParser>> presentationTypeMap = new HashMap<String, Class<? extends IPresentationParser>>();
	
	/**
	 * Initialize a set of default associations. These can be overwritten in an extending class in order to implement
	 * custom behavior. Eventually the Map will be private, and the logic for add/delete/update will be controlled by
	 * the class, not by the caller
	 */
	static {
		presentationTypeMap.put( ".vm", ParseVelocity.class );
		presentationTypeMap.put( ".fm", ParseFreeMarker.class );
	}
	
	/**
	 * This class must be constructed with a file, which will be analyzed
	 * @param file
	 */
	public PresentationType( File file ) {
		setFile( file );
	}

	private void setFile( File file ) {
		this.file = file;
	}

	/**
	 * Allows you to inject the correct Parsing class in the right place, bypassing the need to call the determineParsingClass method
	 * @param parsingClass
	 */
	protected void setParsingClass( Class<? extends IPresentationParser> parsingClass ) {
		this.parsingClass = parsingClass;
	}
	
	protected Class<? extends IPresentationParser> getParsingClass() {
		return this.parsingClass;
	}
	
	/**
	 * This method should allow you to inject different logic for figuring out the parser to be matched
	 */
	public void determineParsingClass() {
		String fileExtension = this.file.getName().substring( this.file.getName().lastIndexOf( "." ) );
		
		//Iterate through the file extensions and try to find a match for the Parsing Class
		for ( String extension: presentationTypeMap.keySet() ) {
			if ( fileExtension.equals( extension ) ) {
				this.setParsingClass( presentationTypeMap.get( extension ) );
			}
		}
		
		if ( this.parsingClass == null ) {
			throw new RuntimeException( String.format( "Could not find a matching Parser class for file [%s]", file.getName() ) );
		}
	}
	
	/**
	 * Return a fully constructed version of the Parsing Class associated with this file 
	 * @return
	 */
	public IPresentationParser getParser() {
		try {
			Constructor<? extends IPresentationParser> constructor = this.getParsingClass().getConstructor( File.class );
			constructor.setAccessible( true );
			return ( IPresentationParser ) constructor.newInstance( file );
		}
		catch ( InvocationTargetException ex ) {
			throw new RuntimeException( String.format( "Could not construct Parsing class [%s]: [%s]", this.getParsingClass(), ex.getTargetException() ) );
		}
		catch ( Exception ex ) {
			throw new RuntimeException( String.format( "Could not construct Parsing class [%s]: [%s]", this.getParsingClass(), ex.toString() ) );
		}
	}
}
