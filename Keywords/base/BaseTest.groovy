package com.katalon.framework.base

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

/**
 * BaseTest
 * 
 * This is the base class for all test cases in the framework.
 * It provides common functionality and hooks for setup and teardown operations.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only test lifecycle management
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Subclasses can be used wherever BaseTest is expected
 * - Interface Segregation: Provides only methods needed by all tests
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
abstract class BaseTest {
    
    protected String testName
    protected Map<String, Object> testData
    protected boolean isSetupComplete = false
    
    /**
     * Constructor
     * @param testName The name of the test
     */
    BaseTest(String testName) {
        this.testName = testName
        this.testData = [:]
    }
    
    /**
     * Setup method to be called before test execution
     * This is a template method that calls abstract methods to be implemented by subclasses
     */
    @SetUp
    void setup() {
        try {
            logInfo("Starting test: ${testName}")
            
            // Initialize test data
            initializeTestData()
            
            // Perform setup specific to the test type
            doSetup()
            
            isSetupComplete = true
            logInfo("Setup completed successfully")
        } catch (Exception e) {
            logError("Setup failed: ${e.message}")
            throw e
        }
    }
    
    /**
     * Teardown method to be called after test execution
     * This is a template method that calls abstract methods to be implemented by subclasses
     */
    @TearDown
    void teardown() {
        try {
            logInfo("Finishing test: ${testName}")
            
            // Perform teardown specific to the test type
            doTeardown()
            
            logInfo("Teardown completed successfully")
        } catch (Exception e) {
            logError("Teardown failed: ${e.message}")
            throw e
        }
    }
    
    /**
     * Abstract method to initialize test data
     * To be implemented by subclasses
     */
    protected abstract void initializeTestData()
    
    /**
     * Abstract method for setup operations specific to the test type
     * To be implemented by subclasses
     */
    protected abstract void doSetup()
    
    /**
     * Abstract method for teardown operations specific to the test type
     * To be implemented by subclasses
     */
    protected abstract void doTeardown()
    
    /**
     * Execute the test
     * This is a template method that defines the test execution flow
     */
    void execute() {
        if (!isSetupComplete) {
            setup()
        }
        
        try {
            logInfo("Executing test: ${testName}")
            
            // Execute the test steps
            executeTest()
            
            logInfo("Test execution completed successfully")
        } catch (Exception e) {
            logError("Test execution failed: ${e.message}")
            throw e
        } finally {
            teardown()
        }
    }
    
    /**
     * Abstract method for the actual test execution
     * To be implemented by subclasses
     */
    protected abstract void executeTest()
    
    /**
     * Log an informational message
     * @param message The message to log
     */
    protected void logInfo(String message) {
        KeywordUtil.logInfo("[${testName}] ${message}")
    }
    
    /**
     * Log an error message
     * @param message The error message to log
     */
    protected void logError(String message) {
        KeywordUtil.markFailed("[${testName}] ERROR: ${message}")
    }
    
    /**
     * Log a warning message
     * @param message The warning message to log
     */
    protected void logWarning(String message) {
        KeywordUtil.markWarning("[${testName}] WARNING: ${message}")
    }
    
    /**
     * Get a global variable value
     * @param name The name of the global variable
     * @return The value of the global variable
     */
    protected Object getGlobalVariable(String name) {
        return RunConfiguration.getGlobalVariables().get(name)
    }
    
    /**
     * Set a test data value
     * @param key The key for the test data
     * @param value The value to set
     */
    protected void setTestData(String key, Object value) {
        testData.put(key, value)
    }
    
    /**
     * Get a test data value
     * @param key The key for the test data
     * @return The value of the test data
     */
    protected Object getTestData(String key) {
        return testData.get(key)
    }
}