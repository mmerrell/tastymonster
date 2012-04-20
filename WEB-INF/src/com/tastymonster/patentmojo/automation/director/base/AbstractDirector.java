package com.tastymonster.patentmojo.automation.director.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.tastymonster.patentmojo.automation.page.base.IAutomationPage;


public class AbstractDirector {

	/**
	 * Allows you to construct a Page object by using reflection to bypass the access restrictions. This allows the director
	 * layer to instantiate a page object, and prevents any other class hierarchy from being able to do so
	 * @param <T>
	 * @param pageClass
	 * @return
	 */
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
