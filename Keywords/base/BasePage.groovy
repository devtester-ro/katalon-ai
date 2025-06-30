package com.katalon.framework.base

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import com.kms.katalon.core.webui.driver.DriverFactory

/**
 * BasePage
 * 
 * This is the base class for all page objects in the framework.
 * It provides common functionality for interacting with web pages.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only page interactions
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Subclasses can be used wherever BasePage is expected
 * - Interface Segregation: Provides only methods needed by all pages
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
abstract class BasePage {
    
    protected String pageName
    protected int defaultTimeout = 30
    
    /**
     * Constructor
     * @param pageName The name of the page
     */
    BasePage(String pageName) {
        this.pageName = pageName
    }
    
    /**
     * Verify that the page is loaded
     * This is a template method that calls abstract methods to be implemented by subclasses
     * @return true if the page is loaded, false otherwise
     */
    boolean isPageLoaded() {
        try {
            logInfo("Verifying that page is loaded")
            return verifyPageElements()
        } catch (Exception e) {
            logError("Failed to verify page is loaded: ${e.message}")
            return false
        }
    }
    
    /**
     * Abstract method to verify page elements
     * To be implemented by subclasses
     * @return true if all required elements are present, false otherwise
     */
    protected abstract boolean verifyPageElements()
    
    /**
     * Wait for an element to be present
     * @param testObject The element to wait for
     * @param timeout The maximum time to wait in seconds
     * @return true if the element is present, false otherwise
     */
    protected boolean waitForElement(TestObject testObject, int timeout = defaultTimeout) {
        try {
            WebUI.waitForElementPresent(testObject, timeout)
            return true
        } catch (Exception e) {
            logError("Element not present after ${timeout} seconds: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Wait for an element to be visible
     * @param testObject The element to wait for
     * @param timeout The maximum time to wait in seconds
     * @return true if the element is visible, false otherwise
     */
    protected boolean waitForElementVisible(TestObject testObject, int timeout = defaultTimeout) {
        try {
            WebUI.waitForElementVisible(testObject, timeout)
            return true
        } catch (Exception e) {
            logError("Element not visible after ${timeout} seconds: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Wait for an element to be clickable
     * @param testObject The element to wait for
     * @param timeout The maximum time to wait in seconds
     * @return true if the element is clickable, false otherwise
     */
    protected boolean waitForElementClickable(TestObject testObject, int timeout = defaultTimeout) {
        try {
            WebUI.waitForElementClickable(testObject, timeout)
            return true
        } catch (Exception e) {
            logError("Element not clickable after ${timeout} seconds: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Click on an element
     * @param testObject The element to click
     * @param timeout The maximum time to wait in seconds
     * @return true if the click was successful, false otherwise
     */
    protected boolean click(TestObject testObject, int timeout = defaultTimeout) {
        try {
            if (waitForElementClickable(testObject, timeout)) {
                WebUI.click(testObject)
                return true
            }
            return false
        } catch (Exception e) {
            logError("Failed to click element: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Set text in an input field
     * @param testObject The input field
     * @param text The text to set
     * @param timeout The maximum time to wait in seconds
     * @return true if the text was set successfully, false otherwise
     */
    protected boolean setText(TestObject testObject, String text, int timeout = defaultTimeout) {
        try {
            if (waitForElementVisible(testObject, timeout)) {
                WebUI.setText(testObject, text)
                return true
            }
            return false
        } catch (Exception e) {
            logError("Failed to set text in element: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Get text from an element
     * @param testObject The element to get text from
     * @param timeout The maximum time to wait in seconds
     * @return The text of the element, or null if the element is not found
     */
    protected String getText(TestObject testObject, int timeout = defaultTimeout) {
        try {
            if (waitForElementVisible(testObject, timeout)) {
                return WebUI.getText(testObject)
            }
            return null
        } catch (Exception e) {
            logError("Failed to get text from element: ${testObject.getObjectId()}")
            return null
        }
    }
    
    /**
     * Verify that an element is present
     * @param testObject The element to verify
     * @param timeout The maximum time to wait in seconds
     * @return true if the element is present, false otherwise
     */
    protected boolean verifyElementPresent(TestObject testObject, int timeout = defaultTimeout) {
        try {
            return WebUI.verifyElementPresent(testObject, timeout, FailureHandling.OPTIONAL)
        } catch (Exception e) {
            logError("Element not present: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Verify that an element is visible
     * @param testObject The element to verify
     * @param timeout The maximum time to wait in seconds
     * @return true if the element is visible, false otherwise
     */
    protected boolean verifyElementVisible(TestObject testObject, int timeout = defaultTimeout) {
        try {
            return WebUI.verifyElementVisible(testObject, FailureHandling.OPTIONAL)
        } catch (Exception e) {
            logError("Element not visible: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Verify that an element contains text
     * @param testObject The element to verify
     * @param text The text to check for
     * @param timeout The maximum time to wait in seconds
     * @return true if the element contains the text, false otherwise
     */
    protected boolean verifyElementText(TestObject testObject, String text, int timeout = defaultTimeout) {
        try {
            if (waitForElementVisible(testObject, timeout)) {
                String actualText = WebUI.getText(testObject)
                return actualText.contains(text)
            }
            return false
        } catch (Exception e) {
            logError("Failed to verify element text: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Wait for page to load completely
     * @param timeout The maximum time to wait in seconds
     * @return true if the page loaded, false otherwise
     */
    protected boolean waitForPageLoad(int timeout = defaultTimeout) {
        try {
            WebDriver driver = DriverFactory.getWebDriver()
            WebDriverWait wait = new WebDriverWait(driver, timeout)
            wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"))
            return true
        } catch (Exception e) {
            logError("Page did not load completely after ${timeout} seconds")
            return false
        }
    }
    
    /**
     * Log an informational message
     * @param message The message to log
     */
    protected void logInfo(String message) {
        KeywordUtil.logInfo("[${pageName}] ${message}")
    }
    
    /**
     * Log an error message
     * @param message The error message to log
     */
    protected void logError(String message) {
        KeywordUtil.markWarning("[${pageName}] ERROR: ${message}")
    }
    
    /**
     * Log a warning message
     * @param message The warning message to log
     */
    protected void logWarning(String message) {
        KeywordUtil.markWarning("[${pageName}] WARNING: ${message}")
    }
}