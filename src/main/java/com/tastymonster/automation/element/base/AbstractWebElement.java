package com.tastymonster.automation.element.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.tastymonster.automation.base.IAutomationFacade;
import com.tastymonster.automation.base.WebDriverContext;
import com.tastymonster.automation.page.base.AbstractAutomationPage;

/**
 * This is how we keep the Selenium interface at arms' length--this allows us to
 * bend the hierarchy to our will as applicable to our particular problem set.
 * Mostly, this acts as a delegate, but also allows us to tailor the WebElements
 * to our need, to keep end-users and test writers safe from Selenium's
 * ever-changing API, and to lay the groundwork for page transitions, tabs, etc
 * that will make peoples' lives easier
 * 
 * @author mmerrell
 * 
 */
public abstract class AbstractWebElement implements IBaseWebElement {
    protected By by;
    protected TabWebElement tab;
    protected AbstractAutomationPage page;

    public AbstractWebElement(String id, AbstractAutomationPage page) {
        this(id, null, page);
    }

    public AbstractWebElement(String id, TabWebElement tab,
            AbstractAutomationPage page) {
        super();
        this.by = By.id(id);
        this.tab = tab;
        this.page = page;
    }

    public By getBy() {
        return by;
    }

    public String getId() {
        Pattern p = Pattern.compile("By.id:\\s*(.*)");
        Matcher m = p.matcher(by.toString());
        if (m.find()) {
            return m.group(1); // should contain only the id
        }
        return by.toString(); // TODO This will work until we can successfully
                              // replace all 'id' references to 'by' references
    }

    public void setId(String id) {
        this.by = By.id(id);
    }

    /**
     * A Tab is intended to reflect the control mechanism of any sort of
     * "container" of a page that can change views, when these views can conceal
     * or reveal WebElements.
     * 
     * For example, consider a web form on a single page, which has multiple
     * fields spread across three tabs, where some fields are obscured when one
     * tab is displayed. This "tab" element represents the control you need to
     * click on in order to reveal "this" web element. It usually isn't a parent
     * in the DOM--usually it's a separate Clickable element somewhere unrelated
     * to "this" element.
     * 
     * So if the userName, password, address, and phone number fields are all
     * accessible only after clicking the "Personal Info" tab, setting this Tab
     * element on the object itself will force the framework to click the Tab
     * before trying to locate or interact with "this" element. This all happens
     * under the covers, "for free"
     * 
     * @return
     */
    public TabWebElement getTab() {
        return tab;
    }

    public void setTab(TabWebElement tab) {
        this.tab = tab;
    }

    public AbstractAutomationPage getPage() {
        return page;
    }

    public void setPage(AbstractAutomationPage page) {
        this.page = page;
    }

    public WebElement getWebElement() {
        return this.getWebElement(getBy());
    }

    protected WebElement getWebElement(String id) {
        return this.getWebElement(By.id(id));
    }

    private WebElement getWebElement(By by) {
        return getDriver().findElement(by);
    }

    protected IAutomationFacade getDriver() {
        return WebDriverContext.getDriver();
    }

    @Override
    public boolean isDisplayed() {
        return getDriver().findElement(by).isDisplayed();
    }

    @Override
    public String getAttribute(String attrName) {
        return getDriver().findElement(by).getAttribute(attrName);
    }
}
