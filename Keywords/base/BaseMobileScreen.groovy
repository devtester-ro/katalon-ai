package com.katalon.framework.base

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil

/**
 * BaseMobileScreen
 * 
 * This is the base class for all mobile screen objects in the framework.
 * It provides common functionality for interacting with mobile app screens.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only mobile screen interactions
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Subclasses can be used wherever BaseMobileScreen is expected
 * - Interface Segregation: Provides only methods needed by all mobile screens
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
abstract class BaseMobileScreen {
    
    protected String screenName
    protected int defaultTimeout = 30
    
    /**
     * Constructor
     * @param screenName The name of the screen
     */
    BaseMobileScreen(String screenName) {
        this.screenName = screenName
    }
    
    /**
     * Verify that the screen is loaded
     * This is a template method that calls abstract methods to be implemented by subclasses
     * @return true if the screen is loaded, false otherwise
     */
    boolean isScreenLoaded() {
        try {
            logInfo("Verifying that screen is loaded")
            return verifyScreenElements()
        } catch (Exception e) {
            logError("Failed to verify screen is loaded: ${e.message}")
            return false
        }
    }
    
    /**
     * Abstract method to verify screen elements
     * To be implemented by subclasses
     * @return true if all required elements are present, false otherwise
     */
    protected abstract boolean verifyScreenElements()
    
    /**
     * Wait for an element to exist
     * @param testObject The element to wait for
     * @param timeout The maximum time to wait in seconds
     * @return true if the element exists, false otherwise
     */
    protected boolean waitForElement(TestObject testObject, int timeout = defaultTimeout) {
        try {
            Mobile.waitForElementPresent(testObject, timeout)
            return true
        } catch (Exception e) {
            logError("Element not present after ${timeout} seconds: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Tap on an element
     * @param testObject The element to tap
     * @param timeout The maximum time to wait in seconds
     * @return true if the tap was successful, false otherwise
     */
    protected boolean tap(TestObject testObject, int timeout = defaultTimeout) {
        try {
            if (waitForElement(testObject, timeout)) {
                Mobile.tap(testObject, timeout)
                return true
            }
            return false
        } catch (Exception e) {
            logError("Failed to tap element: ${testObject.getObjectId()}")
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
            if (waitForElement(testObject, timeout)) {
                Mobile.setText(testObject, text, timeout)
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
            if (waitForElement(testObject, timeout)) {
                return Mobile.getText(testObject, timeout)
            }
            return null
        } catch (Exception e) {
            logError("Failed to get text from element: ${testObject.getObjectId()}")
            return null
        }
    }
    
    /**
     * Verify that an element exists
     * @param testObject The element to verify
     * @param timeout The maximum time to wait in seconds
     * @return true if the element exists, false otherwise
     */
    protected boolean verifyElementExist(TestObject testObject, int timeout = defaultTimeout) {
        try {
            return Mobile.verifyElementExist(testObject, timeout, FailureHandling.OPTIONAL)
        } catch (Exception e) {
            logError("Element does not exist: ${testObject.getObjectId()}")
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
            return Mobile.verifyElementVisible(testObject, timeout, FailureHandling.OPTIONAL)
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
            if (waitForElement(testObject, timeout)) {
                String actualText = Mobile.getText(testObject, timeout)
                return actualText.contains(text)
            }
            return false
        } catch (Exception e) {
            logError("Failed to verify element text: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Swipe on the screen
     * @param startX The starting X coordinate
     * @param startY The starting Y coordinate
     * @param endX The ending X coordinate
     * @param endY The ending Y coordinate
     * @return true if the swipe was successful, false otherwise
     */
    protected boolean swipe(int startX, int startY, int endX, int endY) {
        try {
            Mobile.swipe(startX, startY, endX, endY)
            return true
        } catch (Exception e) {
            logError("Failed to swipe: ${e.message}")
            return false
        }
    }
    
    /**
     * Scroll to an element
     * @param testObject The element to scroll to
     * @param timeout The maximum time to wait in seconds
     * @return true if the scroll was successful, false otherwise
     */
    protected boolean scrollToElement(TestObject testObject, int timeout = defaultTimeout) {
        try {
            Mobile.scrollToText(Mobile.getText(testObject, 1, FailureHandling.OPTIONAL))
            return verifyElementExist(testObject, timeout)
        } catch (Exception e) {
            logError("Failed to scroll to element: ${testObject.getObjectId()}")
            return false
        }
    }
    
    /**
     * Take a screenshot
     * @param fileName The name of the screenshot file
     * @return true if the screenshot was taken successfully, false otherwise
     */
    protected boolean takeScreenshot(String fileName) {
        try {
            Mobile.takeScreenshot(fileName)
            logInfo("Screenshot taken: ${fileName}")
            return true
        } catch (Exception e) {
            logError("Failed to take screenshot: ${e.message}")
            return false
        }
    }
    
    /**
     * Press the back button
     * @return true if the back button was pressed successfully, false otherwise
     */
    protected boolean pressBack() {
        try {
            Mobile.pressBack()
            return true
        } catch (Exception e) {
            logError("Failed to press back button: ${e.message}")
            return false
        }
    }
    
    /**
     * Log an informational message
     * @param message The message to log
     */
    protected void logInfo(String message) {
        KeywordUtil.logInfo("[${screenName}] ${message}")
    }
    
    /**
     * Log an error message
     * @param message The error message to log
     */
    protected void logError(String message) {
        KeywordUtil.markWarning("[${screenName}] ERROR: ${message}")
    }
    
    /**
     * Log a warning message
     * @param message The warning message to log
     */
    protected void logWarning(String message) {
        KeywordUtil.markWarning("[${screenName}] WARNING: ${message}")
    }
}