package com.tastymonster.automation.codegen;

import java.io.File;
import java.util.List;

public interface IPresentationLayerInfo {

	/**
	 * Returns the list of files to be processed for generation
	 * @return
	 */
	List<File> getFileList();

	/**
	 * Returns the path where the BasePage objects will be stored
	 * @return
	 */
	String getBasePagePath();

	/**
	 * Returns the path where the DerivedPage objects will be stored
	 * @return
	 */
	String getDerivedPagePath();

}