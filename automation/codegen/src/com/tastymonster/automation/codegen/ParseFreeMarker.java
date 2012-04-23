package com.tastymonster.automation.codegen;

import java.util.Set;

public class ParseFreeMarker implements IPresentationParser {

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

	protected String normalizeFieldName(String string) {
		return null;
	}

	@Override
	public void initPageContents() {
	}

}
