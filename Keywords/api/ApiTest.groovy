package com.katalon.framework.api

import com.katalon.framework.base.BaseTest
import com.katalon.framework.util.ConfigurationManager
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

/**
 * ApiTest
 * 
 * This class extends BaseTest to provide common functionality for API tests.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only API test lifecycle management
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Subclasses can be used wherever ApiTest is expected
 * - Interface Segregation: Provides only methods needed by API tests
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
class ApiTest extends BaseTest {
    
    protected String apiBaseUrl
    protected String apiKey
    
    /**
     * Constructor
     * @param testName The name of the test
     */
    ApiTest(String testName) {
        super(testName)
    }
    
    /**
     * Initialize test data
     */
    @Override
    protected void initializeTestData() {
        // Get configuration values
        ConfigurationManager configManager = ConfigurationManager.getInstance()
        apiBaseUrl = configManager.getApiBaseUrl()
        apiKey = configManager.getConfig("apiKey", "")
        
        // Set test data
        setTestData("apiBaseUrl", apiBaseUrl)
        setTestData("apiKey", apiKey)
        
        logInfo("Test data initialized: apiBaseUrl=${apiBaseUrl}")
    }
    
    /**
     * Setup operations specific to API tests
     */
    @Override
    protected void doSetup() {
        // No specific setup needed for API tests
        logInfo("API test setup completed")
    }
    
    /**
     * Teardown operations specific to API tests
     */
    @Override
    protected void doTeardown() {
        // No specific teardown needed for API tests
        logInfo("API test teardown completed")
    }
    
    /**
     * Verify response status code
     * @param response The response object
     * @param expectedStatusCode The expected status code
     * @return true if the status code matches, false otherwise
     */
    protected boolean verifyStatusCode(ResponseObject response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode()
        if (actualStatusCode == expectedStatusCode) {
            logInfo("Status code verification passed: ${actualStatusCode}")
            return true
        } else {
            logError("Status code verification failed. Expected: ${expectedStatusCode}, Actual: ${actualStatusCode}")
            return false
        }
    }
    
    /**
     * Verify response contains a specific property
     * @param response The response object
     * @param propertyName The property name to check
     * @return true if the property exists, false otherwise
     */
    protected boolean verifyResponseContainsProperty(ResponseObject response, String propertyName) {
        try {
            Object value = WS.getElementPropertyValue(response, propertyName)
            if (value != null) {
                logInfo("Response contains property: ${propertyName}")
                return true
            } else {
                logError("Response does not contain property: ${propertyName}")
                return false
            }
        } catch (Exception e) {
            logError("Failed to verify response property: ${e.message}")
            return false
        }
    }
    
    /**
     * Verify response property value
     * @param response The response object
     * @param propertyName The property name to check
     * @param expectedValue The expected value
     * @return true if the property value matches, false otherwise
     */
    protected boolean verifyPropertyValue(ResponseObject response, String propertyName, Object expectedValue) {
        try {
            Object actualValue = WS.getElementPropertyValue(response, propertyName)
            boolean matches = WS.verifyElementPropertyValue(response, propertyName, expectedValue, false)
            if (matches) {
                logInfo("Property ${propertyName} has expected value: ${expectedValue}")
                return true
            } else {
                logError("Property ${propertyName} value mismatch. Expected: ${expectedValue}, Actual: ${actualValue}")
                return false
            }
        } catch (Exception e) {
            logError("Failed to verify property value: ${e.message}")
            return false
        }
    }
    
    /**
     * Get response body as string
     * @param response The response object
     * @return The response body as string
     */
    protected String getResponseBodyAsString(ResponseObject response) {
        try {
            return response.getResponseBodyContent()
        } catch (Exception e) {
            logError("Failed to get response body: ${e.message}")
            return null
        }
    }
    
    /**
     * Log response details
     * @param response The response object
     */
    protected void logResponse(ResponseObject response) {
        int statusCode = response.getStatusCode()
        String statusText = response.getResponseBodyContent()
        logInfo("Response received: Status ${statusCode}")
        logInfo("Response body: ${statusText}")
    }
}