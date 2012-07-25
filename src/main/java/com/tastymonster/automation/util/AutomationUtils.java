package com.tastymonster.automation.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;


public class AutomationUtils {
	
	/**
	 * Normalizes a field name using the following steps:
	 * 
	 * 1) Removes all non-word characters and underscores
	 * 2) Checks to make sure we're not left with a java keyword
	 * 3) Makes it into proper camelCase
	 * 
	 * It will return an empty string if the initial value passed in or the resulting value after normalization is null or empty.
	 * This allows you the freedom to handle it how you want without the restriction of a try/catch
	 * @param fieldName
	 * @return
	 */
	public static String normalizeFieldName( String fieldName ) {
		//Make sure it isn't null before we start
		if ( StringUtils.isEmpty( fieldName ) ) {
			return "";
		}
		
 		String normalizedFieldName = fieldName.replaceAll( "[^\\w]|_", "" );
 		//Check to see if it's null or empty AFTER normalizing
 		if ( StringUtils.isEmpty( normalizedFieldName ) ) {
 			return "";
 		}
 		
 		if ( reservedKeywords.contains( normalizedFieldName ) ) {
 			normalizedFieldName += "Element";
 		}
 		
 		normalizedFieldName = normalizeCase(normalizedFieldName);
		return normalizedFieldName;
	}
	
	/**
	 * Make sure the first char is lower-case (can be overridden for customized behavior)
	 * @param normalizedFieldName
	 * @return
	 */
	protected static String normalizeCase(String normalizedFieldName) {
		return String.valueOf(  normalizedFieldName.charAt( 0 ) ).toLowerCase() + 
								normalizedFieldName.substring( 1, normalizedFieldName.length() );
	}
	
    private static Set<String> reservedKeywords = new HashSet<String>();
    static {
        reservedKeywords.add( "abstract" );
        reservedKeywords.add( "assert" );
        reservedKeywords.add( "boolean" );
        reservedKeywords.add( "break" );
        reservedKeywords.add( "byte" );
        reservedKeywords.add( "case" );
        reservedKeywords.add( "catch" );
        reservedKeywords.add( "char" );
        reservedKeywords.add( "class" );
        reservedKeywords.add( "const" );
        reservedKeywords.add( "continue" );
        reservedKeywords.add( "default" );
        reservedKeywords.add( "do" );
        reservedKeywords.add( "double" );
        reservedKeywords.add( "else" );
        reservedKeywords.add( "enum" );
        reservedKeywords.add( "extends" );
        reservedKeywords.add( "final" );
        reservedKeywords.add( "finally" );
        reservedKeywords.add( "float" );
        reservedKeywords.add( "for" );
        reservedKeywords.add( "goto" );
        reservedKeywords.add( "if" );
        reservedKeywords.add( "implements" );
        reservedKeywords.add( "import" );
        reservedKeywords.add( "instanceof" );
        reservedKeywords.add( "int" );
        reservedKeywords.add( "interface" );
        reservedKeywords.add( "long" );
        reservedKeywords.add( "native" );
        reservedKeywords.add( "new" );
        reservedKeywords.add( "package" );
        reservedKeywords.add( "private" );
        reservedKeywords.add( "protected" );
        reservedKeywords.add( "public" );
        reservedKeywords.add( "return" );
        reservedKeywords.add( "short" );
        reservedKeywords.add( "static" );
        reservedKeywords.add( "strictfp" );
        reservedKeywords.add( "super" );
        reservedKeywords.add( "switch" );
        reservedKeywords.add( "synchronized" );
        reservedKeywords.add( "this" );
        reservedKeywords.add( "throw" );
        reservedKeywords.add( "throws" );
        reservedKeywords.add( "transient " );
        reservedKeywords.add( "try" );
        reservedKeywords.add( "void" );
        reservedKeywords.add( "volatile" );
        reservedKeywords.add( "while" );
    }
}
