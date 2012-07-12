package com.tastymonster.automation.director.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.tastymonster.automation.element.base.ISettable;
import com.tastymonster.automation.page.base.IAutomationPage;

public class AbstractFields {

	protected List<ISettable<?>> fields = new ArrayList<ISettable<?>>();

	protected <T extends IAutomationPage> T newPage( Class<T> pageClass ) {
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

	/**
	 * Persists all Settable fields to the browser
	 */
	public void persist() {
		for ( ISettable<?> field: fields ) {
			if ( field.hasValue() ) {
				field.setWebValue();
			}
		}
	}
}
