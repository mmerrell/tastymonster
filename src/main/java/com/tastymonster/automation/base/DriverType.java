package com.tastymonster.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public enum DriverType {
    Firefox(FirefoxDriver.class), InternetExplorer(InternetExplorerDriver.class), Chrome(
            ChromeDriver.class), Other(null);

    Class<? extends WebDriver> driverClass;

    DriverType(Class<? extends WebDriver> driverClass) {
        this.driverClass = driverClass;
    }

    public Class<? extends WebDriver> getDriver() {
        return this.driverClass;
    }

    public static DriverType valueOf(Class<? extends WebDriver> clazz) {
        for (DriverType driverType : DriverType.values()) {
            // Need to check for null here--the value sent in could be a driver
            // type we haven't implemented yet
            if (driverType.driverClass != null
                    && driverType.driverClass.isAssignableFrom(clazz)) {
                return driverType;
            }
        }
        return Other;
    }
}
