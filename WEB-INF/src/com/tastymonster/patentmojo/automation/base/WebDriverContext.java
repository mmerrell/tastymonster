package com.tastymonster.patentmojo.automation.base;

public class WebDriverContext {
	
	private static IAutomationFacade driver;
	
	/**
	 * Returns the current instance of the WebDriver
	 * @return
	 */
	public static IAutomationFacade getDriver() {
		if ( driver == null ) {
			try {
				driver = new WebDriverFacade( DriverType.Firefox.driverClass.newInstance() );
			} catch ( Exception e ) {
				throw new RuntimeException( "Error instantiating driver " + DriverType.Firefox.toString() );
			}
		}
		return driver;
	}
}
