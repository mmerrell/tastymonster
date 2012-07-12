package com.tastymonster.automation.director.base;

public interface IAutomationFields {

	/**
	 * Persists all fields to the UI (if fields use tabs, they should be persisted in such
	 * a way that each tab is loaded only once
	 */
	void persistAllFields();
	
	/**
	 * Persists only those fields flagged as required. Very useful if certain fields only
	 * appear after clicking a save button--this allows you to sequence the commands like so:
	 * 
	 * fields.persistRequiredFields()
	 * fields.save.click()
	 * fields.persistNonRequiredFields()
	 */
	void persistRequiredFields();
	
	/**
	 * Persists only the non-required fields
	 */
	void persistNonRequiredFields();
}
