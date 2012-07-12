package com.tastymonster.automation.codegen;

import java.io.File;

public class ParserFactory {
	
	/**
	 * Returns the correct Parsing Class for the given file. Derive the PresentationType class in order to 
	 * implement custom behavior
	 * @param file
	 * @return
	 */
	public static IPresentationParser getParser( File file ) {
		PresentationType type = new PresentationType( file );
		
		//TODO These methods could probably be put somewhere else, I just wanted to keep all these 
		// operations atomic for better testability
		type.determineParsingClass();
		IPresentationParser parser = type.getParser();
		parser.initPageContents();
		return parser;
	}
}
