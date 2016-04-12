package com.tastymonster.automation.element.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType; 
import org.openqa.selenium.WebDriverException; 
import org.openqa.selenium.Rectangle;

public class MockWebElement implements WebElement {

	@Override
	public void clear() {
	}

	@Override
	public void click() {
	}

	@Override
	public WebElement findElement(By arg0) {
		return null;
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		return null;
	}

	@Override
	public String getAttribute(String arg0) {
		return null;
	}

	@Override
	public String getCssValue(String arg0) {
		return null;
	}

	@Override
	public Point getLocation() {
		return null;
	}

	@Override
	public Dimension getSize() {
		return null;
	}

	@Override
	public String getTagName() {
		return null;
	}

	@Override
	public String getText() {
		return null;
	}

	@Override
	public boolean isDisplayed() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public boolean isSelected() {
		return false;
	}

	@Override
	public void sendKeys(CharSequence... arg0) {
	}

	@Override
	public void submit() {
	}
	
	@Override
    public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Rectangle getRect() {
        // TODO Auto-generated method stub
        return null;
    }

}
