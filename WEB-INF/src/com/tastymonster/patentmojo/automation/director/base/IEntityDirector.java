package com.tastymonster.patentmojo.automation.director.base;

public interface IEntityDirector<T extends AbstractFields> {

	/**
	 * Create this entity according to the fields object passed in
	 * @param fields
	 */
	void create( T fields );
	
	/**
	 * Navigates to this entity according to the fields object passed in
	 * @param fields
	 */
	void navigate( T fields );
	
	/**
	 * Navigates to the oldFields entity and updates the entity according to the newFields data
	 * @param oldFields
	 * @param newFields
	 */
	void update( T oldFields, T newFields );
	
	/**
	 * Deletes the specified entity
	 * @param fields
	 */
	void delete( T fields );
	
	/**
	 * Returns true if the specified entity exists, false if it can't be found
	 * @param fields
	 * @return
	 */
	boolean exists( T fields );
	
}
