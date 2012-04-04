package com.tastymonster.patentmojo.automation.codegen;

import java.util.Set;

public interface IPresentationParser {

	Set<FieldDetails> buildFieldDetails();

	String getPageName();

	String getPageURI();

}
