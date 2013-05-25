package com.tastymonster.automation.codegen;

import java.io.File;
import java.util.Set;

public class ParseFreeMarker implements IPresentationParser {

	protected File file;
	
	public ParseFreeMarker( File file ) {
		this.file = file;
	}
	
	@Override
	public Set<FieldDetails> buildFieldDetails() {
		return null;
	}

	@Override
	public String getPageName() {
		return "";
	}

	@Override
	public String getPageURI() {
		return null;
	}

	@Override
	public String normalizeFieldName( String fieldName ) {
		return fieldName;
	}

	@Override
	public void initPageContents() {
	}

}
