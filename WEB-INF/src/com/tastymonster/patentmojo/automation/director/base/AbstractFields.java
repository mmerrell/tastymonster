package com.tastymonster.patentmojo.automation.director.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.tastymonster.patentmojo.automation.page.base.IAutomationPage;

public class AbstractFields {

	protected <T extends IAutomationPage> T newPage( Class<T> pageClass ){
		try {
			Constructor<T> constructor = pageClass.getDeclaredConstructor();
			constructor.setAccessible( true );
			return constructor.newInstance();
		}
		catch ( InvocationTargetException ex ) {
			throw new RuntimeException( String.format( "Could not construct page class [%s]: [%s]", pageClass, ex.getTargetException() ) );
		}
		catch ( Exception ex ) {
			throw new RuntimeException( String.format( "Could not construct page class [%s]: [%s]", pageClass, ex.toString() ) );
		}
	}

}
