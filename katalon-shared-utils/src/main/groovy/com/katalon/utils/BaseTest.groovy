package com.katalon.utils

import com.katalon.config.KatalonConfig
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestName

/**
 * Base test class that provides common setup and teardown functionality
 * This reduces duplication across test implementations
 */
abstract class BaseTest {

    @Rule
    public TestName testName = new TestName()
    
    protected Properties testProperties
    protected long startTime
    
    @Before
    void setUp() {
        startTime = System.currentTimeMillis()
        CommonUtils.logInfo("Starting test: ${testName.methodName}")
        
        // Initialize test properties
        testProperties = new Properties()
        
        // Setup test-specific configuration
        setupTest()
    }
    
    @After
    void tearDown() {
        long duration = System.currentTimeMillis() - startTime
        CommonUtils.logInfo("Finished test: ${testName.methodName} in ${duration}ms")
        
        // Cleanup test-specific resources
        cleanupTest()
    }
    
    /**
     * Override this method to provide test-specific setup
     */
    protected void setupTest() {
        // Default implementation - can be overridden
    }
    
    /**
     * Override this method to provide test-specific cleanup
     */
    protected void cleanupTest() {
        // Default implementation - can be overridden
    }
    
    /**
     * Helper method to set test property
     */
    protected void setTestProperty(String key, String value) {
        testProperties.setProperty(key, value)
    }
    
    /**
     * Helper method to get test property with default
     */
    protected String getTestProperty(String key, String defaultValue = "") {
        return CommonUtils.getProperty(testProperties, key, defaultValue)
    }
    
    /**
     * Helper method to verify expected vs actual with meaningful message
     */
    protected void verifyEquals(Object expected, Object actual, String message) {
        if (expected != actual) {
            String errorMessage = "${message} - Expected: ${expected}, Actual: ${actual}"
            CommonUtils.logError(errorMessage)
            throw new AssertionError(errorMessage)
        }
    }
    
    /**
     * Helper method to verify condition with meaningful message
     */
    protected void verifyTrue(boolean condition, String message) {
        if (!condition) {
            String errorMessage = "${message} - Condition was false"
            CommonUtils.logError(errorMessage)
            throw new AssertionError(errorMessage)
        }
    }
}