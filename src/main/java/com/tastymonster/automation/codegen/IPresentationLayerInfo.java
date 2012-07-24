package com.tastymonster.automation.codegen;

import java.io.File;
import java.util.List;

public interface IPresentationLayerInfo {

	/**
	 * @return The list of files to be processed for generation
	 */
	List<File> getFileList();

	/**
	 * @return The path where the BasePage objects will be stored
	 */
	String getBasePagePath();

	/**
	 * @return The path where the DerivedPage objects will be stored
	 */
	String getDerivedPagePath();
	
	/**
	 * @return The path where the Generated page objects will be stored
	 */
	String getGeneratedPagePath();

	/**
	 * @return The path to get the VM Templates from which the pages will be generated
	 */
	String getCodegenTemplatePath();

        /** 
         * @return The path to the source presentation templates (your product's templates, the ones to be parsed)
         */
        String getTemplatePath();
}
