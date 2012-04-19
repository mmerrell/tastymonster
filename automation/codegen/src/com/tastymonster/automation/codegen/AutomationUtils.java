package com.tastymonster.automation.codegen;


public class AutomationUtils {
	public static String normalizeFieldName( String fieldName ) {
 		String normalizedFieldName = fieldName.replaceAll( "[^\\w]|_", "" );
 		if ( !normalizedFieldName.isEmpty() ) {
 			normalizedFieldName = String.valueOf( normalizedFieldName.charAt( 0 ) ).toLowerCase() + normalizedFieldName.substring( 1, normalizedFieldName.length() );
 		}
		return normalizedFieldName;
	}
}
