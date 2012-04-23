package com.tastymonster.automation.codegen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class PresentationLayerInfo implements IPresentationLayerInfo {
	private List<File> files = new ArrayList<File>();

	/**
	 * Returns the list of files to be parsed
	 * @return
	 */
	public abstract List<File> getFileList();

	/**
	 * Initializes a list of all presentation layer files to be parsed. Each entry contains the path to the file 
	 * as well as the file name
	 */
	public void initFiles() {
		List<File> files = getFileList();
		for ( File file: files ) {
			this.addFile( file );
		}
		this.files = files;
	}

	/**
	 * Add a file to the list of files--this allows for checks to be made on the given file before it is added
	 * @param files
	 * @param path
	 */
	protected void addFile( File file ) {
		//Make sure the file is readable (and perform other checks if necessary
		if ( file.canRead() ) {
			this.files.add( file );
		}
	}
}
