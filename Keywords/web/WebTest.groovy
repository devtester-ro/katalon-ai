package com.katalon.framework.web

import com.katalon.framework.base.BaseTest
import com.katalon.framework.util.ConfigurationManager
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling

/**
 * WebTest
 * 
 * This class extends BaseTest to provide common functionality for web tests.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only web test lifecycle management
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Subclasses can be used wherever WebTest is expected
 * - Interface Segregation: Provides only methods needed by web tests
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
class WebTest extends BaseTest {
    
    protected String baseUrl
    protected String browserType
    
    /**
     * Constructor
     * @param testName The name of the test
     */
    WebTest(String testName) {
        super(testName)
    }
    
    /**
     * Initialize test data
     */
    @Override
    protected void initializeTestData() {
        // Get configuration values
        ConfigurationManager configManager = ConfigurationManager.getInstance()
        baseUrl = configManager.getBaseUrl()
        browserType = configManager.getBrowserType()
        
        // Set test data
        setTestData("baseUrl", baseUrl)
        setTestData("browserType", browserType)
        
        logInfo("Test data initialized: baseUrl=${baseUrl}, browserType=${browserType}")
    }
    
    /**
     * Setup operations specific to web tests
     */
    @Override
    protected void doSetup() {
        // Open browser
        try {
            WebUI.openBrowser('')
            WebUI.maximizeWindow()
            logInfo("Browser opened: ${browserType}")
        } catch (Exception e) {
            logError("Failed to open browser: ${e.message}")
            throw e
        }
    }
    
    /**
     * Teardown operations specific to web tests
     */
    @Override
    protected void doTeardown() {
        // Close browser
        try {
            WebUI.closeBrowser()
            logInfo("Browser closed")
        } catch (Exception e) {
            logError("Failed to close browser: ${e.message}")
            // Don't throw the exception here to ensure teardown completes
        }
    }
    
    /**
     * Take a screenshot
     * @param fileName The name of the screenshot file
     */
    protected void takeScreenshot(String fileName) {
        try {
            WebUI.takeScreenshot(fileName, FailureHandling.CONTINUE_ON_FAILURE)
            logInfo("Screenshot taken: ${fileName}")
        } catch (Exception e) {
            logError("Failed to take screenshot: ${e.message}")
        }
    }
    
    /**
     * Navigate to a URL
     * @param url The URL to navigate to
     */
    protected void navigateTo(String url) {
        try {
            WebUI.navigateToUrl(url)
            logInfo("Navigated to: ${url}")
        } catch (Exception e) {
            logError("Failed to navigate to ${url}: ${e.message}")
            throw e
        }
    }
    
    /**
     * Wait for page to load
     * @param timeout The maximum time to wait in seconds
     */
    protected void waitForPageToLoad(int timeout = 30) {
        try {
            WebUI.waitForPageLoad(timeout)
            logInfo("Page loaded")
        } catch (Exception e) {
            logError("Page did not load within ${timeout} seconds: ${e.message}")
            throw e
        }
    }
    
    /**
     * Verify page title
     * @param expectedTitle The expected title
     * @return true if the title matches, false otherwise
     */
    protected boolean verifyPageTitle(String expectedTitle) {
        try {
            String actualTitle = WebUI.getWindowTitle()
            if (actualTitle.contains(expectedTitle)) {
                logInfo("Page title verification passed: ${actualTitle}")
                return true
            } else {
                logError("Page title verification failed. Expected: ${expectedTitle}, Actual: ${actualTitle}")
                return false
            }
        } catch (Exception e) {
            logError("Failed to verify page title: ${e.message}")
            return false
        }
    }
}