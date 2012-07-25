package com.tastymonster.automation.codegen;

import java.util.Set;

public interface IPresentationParser {

	/**
	 * Builds the FieldDetails Set for the given page, using the correct parsing implementation
	 * @return
	 */
	Set<FieldDetails> buildFieldDetails();

	/**
	 * Returns the name of the Page as displayed in the Browser title bar
	 * @return
	 */
	String getPageName();

	/**
	 * Returns the Locator for this page (be it URL or otherwise)
	 * @return
	 */
	String getPageURI();

	/**
	 * Brings the page "online" by opening it, analyzing the contents, and getting it ready to deliver its contents
	 */
	void initPageContents();
}
