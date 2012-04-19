package com.tastymonster.automation.codegen;

import java.util.Arrays;
import java.util.Map;

import com.tastymonster.automation.element.base.AbstractWebElement;

/**
 * Contains information about the field that will be put in the Fields object (i.e. the "test script" cockpit)
 * @author Marcus
 *
 */
public class FieldDetails implements Comparable<FieldDetails> {

	private String id;
	private String fieldName;
	private Class<? extends AbstractWebElement> type;
	private String pageName;
	private String macroName;
	
	FieldDetails( Map<String, String> fieldAttributes, Class<? extends AbstractWebElement> type, String macroName ) {
		this.setId( fieldAttributes.get( "id" ) );
		this.setType( type );
		this.setMacroName( macroName );
		
		if ( fieldAttributes.containsKey( "fieldName" ) ) {
			this.setFieldName( fieldAttributes.get( "fieldName" ) );
		}
	}
	
	FieldDetails( Map<String, String> fieldAttributes, Class<? extends AbstractWebElement> type, String macroName, String pageName ) {
		this( fieldAttributes, type, macroName );
		this.setPageName( pageName );
	}
	
	public String getId() {
		return id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Class<? extends AbstractWebElement> getType() {
		return type;
	}

	public String getTypeName() {
		return type.getSimpleName();
	}

	/**
	 * This will have the sneaky effect of requiring your "most important" interface be declared first
	 * @return
	 */
	public String getInterface() {
		//TODO Clean this up--this isn't code to be proud of
		return Arrays.asList( type.getInterfaces() ).get( 0 ).getSimpleName();
	}

	public String getPageName() {
		return pageName;
	}

	public String getMacroName() {
		return macroName;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public void setFieldName( String fieldName ) {
		this.fieldName = fieldName;
	}

	public void setType( Class<? extends AbstractWebElement> type ) {
		this.type = type;
	}

	public void setPageName( String pageName ) {
		this.pageName = pageName;
	}

	public void setMacroName( String macroName ) {
		this.macroName = macroName;
	}
	
	@Override
	public int compareTo( FieldDetails o ) {
		return 0;
	}
}
