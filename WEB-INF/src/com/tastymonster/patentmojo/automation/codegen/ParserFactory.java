package com.tastymonster.patentmojo.automation.codegen;

import java.io.File;

public class ParserFactory {
	
	public static IPresentationParser getParser( File file ) {
		// Eventually, this will figure out what parser it needs to return based on some criteria (e.g. file type)
		ParseVelocity parser = new ParseVelocity( file );
		parser.initPageContents();
		return parser;
	}
}
