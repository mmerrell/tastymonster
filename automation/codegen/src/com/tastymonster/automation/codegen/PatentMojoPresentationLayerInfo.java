package com.tastymonster.automation.codegen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PatentMojoPresentationLayerInfo extends PresentationLayerInfo implements IPresentationLayerInfo {

	private static final String TEMPLATE_PATH = "WEB-INF/templates/";

	@Override
	public List<File> getFileList() {
		List<File> list = new ArrayList<File>();
		list.add( new File( TEMPLATE_PATH + "index.vm" ) );
		list.add( new File( TEMPLATE_PATH + "footer.vm" ) );
		list.add( new File( TEMPLATE_PATH + "login.vm" ) );
		list.add( new File( TEMPLATE_PATH + "createUser.vm" ) );
		list.add( new File( TEMPLATE_PATH + "landing.vm" ) );
		list.add( new File( TEMPLATE_PATH + "login.vm" ) );
		list.add( new File( TEMPLATE_PATH + "addPatent.vm" ) );
		return list;
	}

}
