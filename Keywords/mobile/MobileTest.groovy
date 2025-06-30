package com.katalon.framework.mobile

import com.katalon.framework.base.BaseTest
import com.katalon.framework.util.ConfigurationManager
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject

/**
 * MobileTest
 * 
 * This class extends BaseTest to provide common functionality for mobile tests.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only mobile test lifecycle management
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Subclasses can be used wherever MobileTest is expected
 * - Interface Segregation: Provides only methods needed by mobile tests
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
class MobileTest extends BaseTest {

    protected String mobileAppPath
    protected String deviceName
    protected String platformName
    protected String platformVersion
    protected String appiumServerUrl

    /**
     * Constructor
     * @param testName The name of the test
     */
    MobileTest(String testName) {
        super(testName)
    }

    /**
     * Initialize test data
     */
    @Override
    protected void initializeTestData() {
        // Get configuration values
        ConfigurationManager configManager = ConfigurationManager.getInstance()
        mobileAppPath = configManager.getMobileAppPath()
        deviceName = configManager.getDeviceName()
        platformName = configManager.getPlatformName()
        platformVersion = configManager.getPlatformVersion()
        appiumServerUrl = configManager.getAppiumServerUrl()

        // Set test data
        setTestData("mobileAppPath", mobileAppPath)
        setTestData("deviceName", deviceName)
        setTestData("platformName", platformName)
        setTestData("platformVersion", platformVersion)
        setTestData("appiumServerUrl", appiumServerUrl)

        logInfo("Test data initialized: platformName=${platformName}, deviceName=${deviceName}")
    }

    /**
     * Setup operations specific to mobile tests
     */
    @Override
    protected void doSetup() {
        // Start the application
        try {
            Mobile.startApplication(mobileAppPath, true)
            logInfo("Application started: ${mobileAppPath}")
        } catch (Exception e) {
            logError("Failed to start application: ${e.message}")
            throw e
        }
    }

    /**
     * Teardown operations specific to mobile tests
     */
    @Override
    protected void doTeardown() {
        // Close the application
        try {
            Mobile.closeApplication()
            logInfo("Application closed")
        } catch (Exception e) {
            logError("Failed to close application: ${e.message}")
            // Don't throw the exception here to ensure teardown completes
        }
    }

    /**
     * Take a screenshot
     * @param fileName The name of the screenshot file
     */
    protected void takeScreenshot(String fileName) {
        try {
            Mobile.takeScreenshot(fileName, FailureHandling.CONTINUE_ON_FAILURE)
            logInfo("Screenshot taken: ${fileName}")
        } catch (Exception e) {
            logError("Failed to take screenshot: ${e.message}")
        }
    }

    /**
     * Press the back button
     */
    protected void pressBack() {
        try {
            Mobile.pressBack()
            logInfo("Back button pressed")
        } catch (Exception e) {
            logError("Failed to press back button: ${e.message}")
        }
    }

    /**
     * Wait for an element to exist
     * @param testObject The element to wait for
     * @param timeout The maximum time to wait in seconds
     * @return true if the element exists, false otherwise
     */
    protected boolean waitForElement(TestObject testObject, int timeout = 30) {
        try {
            Mobile.waitForElementPresent(testObject, timeout)
            logInfo("Element present: ${testObject.getObjectId()}")
            return true
        } catch (Exception e) {
            logError("Element not present after ${timeout} seconds: ${testObject.getObjectId()}")
            return false
        }
    }

    /**
     * Verify that an element exists
     * @param testObject The element to verify
     * @param timeout The maximum time to wait in seconds
     * @return true if the element exists, false otherwise
     */
    protected boolean verifyElementExists(TestObject testObject, int timeout = 10) {
        try {
            boolean exists = Mobile.verifyElementExist(testObject, timeout, FailureHandling.OPTIONAL)
            if (exists) {
                logInfo("Element exists: ${testObject.getObjectId()}")
            } else {
                logError("Element does not exist: ${testObject.getObjectId()}")
            }
            return exists
        } catch (Exception e) {
            logError("Failed to verify element exists: ${e.message}")
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
    protected boolean verifyElementText(TestObject testObject, String text, int timeout = 10) {
        try {
            if (waitForElement(testObject, timeout)) {
                String actualText = Mobile.getText(testObject, timeout)
                boolean contains = actualText.contains(text)
                if (contains) {
                    logInfo("Element text contains '${text}': ${actualText}")
                } else {
                    logError("Element text does not contain '${text}': ${actualText}")
                }
                return contains
            }
            return false
        } catch (Exception e) {
            logError("Failed to verify element text: ${e.message}")
            return false
        }
    }
}
