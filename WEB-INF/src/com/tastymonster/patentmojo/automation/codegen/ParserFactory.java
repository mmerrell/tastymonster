package com.tastymonster.patentmojo.automation.codegen;

import java.io.File;

public class ParserFactory {
	
	public static IPresentationParser getParser( File file ) {
		ParseVelocity parser = new ParseVelocity( file );
		parser.initPageContents();
		return parser;
	}
}
