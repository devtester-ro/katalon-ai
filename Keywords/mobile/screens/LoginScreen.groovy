package com.katalon.framework.mobile.screens

import com.katalon.framework.base.BaseMobileScreen
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

/**
 * LoginScreen
 * 
 * This class represents the login screen of the mobile application.
 * It extends BaseMobileScreen and provides methods for interacting with the login screen.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only login screen interactions
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Can be used wherever BaseMobileScreen is expected
 * - Interface Segregation: Provides only methods needed for the login screen
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
class LoginScreen extends BaseMobileScreen {
    
    // Screen elements
    private final TestObject usernameField = findTestObject('Object Repository/Mobile/LoginScreen/username_field')
    private final TestObject passwordField = findTestObject('Object Repository/Mobile/LoginScreen/password_field')
    private final TestObject loginButton = findTestObject('Object Repository/Mobile/LoginScreen/login_button')
    
    /**
     * Constructor
     */
    LoginScreen() {
        super("Login Screen")
    }
    
    /**
     * Verify that the screen elements are present
     * @return true if all required elements are present, false otherwise
     */
    @Override
    protected boolean verifyScreenElements() {
        return verifyElementExist(usernameField) && 
               verifyElementExist(passwordField) && 
               verifyElementExist(loginButton)
    }
    
    /**
     * Enter username
     * @param username The username to enter
     * @return true if the username was entered successfully, false otherwise
     */
    boolean enterUsername(String username) {
        return setText(usernameField, username)
    }
    
    /**
     * Enter password
     * @param password The password to enter
     * @return true if the password was entered successfully, false otherwise
     */
    boolean enterPassword(String password) {
        return setText(passwordField, password)
    }
    
    /**
     * Click the login button
     * @return true if the button was clicked successfully, false otherwise
     */
    boolean clickLoginButton() {
        return tap(loginButton)
    }
    
    /**
     * Login with the provided credentials
     * @param username The username
     * @param password The password
     * @return true if the login was successful, false otherwise
     */
    boolean login(String username, String password) {
        logInfo("Logging in with username: ${username}")
        
        if (!enterUsername(username)) {
            logError("Failed to enter username")
            return false
        }
        
        if (!enterPassword(password)) {
            logError("Failed to enter password")
            return false
        }
        
        if (!clickLoginButton()) {
            logError("Failed to click login button")
            return false
        }
        
        logInfo("Login attempt completed")
        return true
    }
    
    /**
     * Take a screenshot of the login screen
     * @param fileName The name of the screenshot file
     * @return true if the screenshot was taken successfully, false otherwise
     */
    boolean takeLoginScreenshot(String fileName) {
        return takeScreenshot(fileName)
    }
    
    /**
     * Clear the username field
     * @return true if the field was cleared successfully, false otherwise
     */
    boolean clearUsername() {
        try {
            if (waitForElement(usernameField)) {
                Mobile.clearText(usernameField, 5)
                return true
            }
            return false
        } catch (Exception e) {
            logError("Failed to clear username field: ${e.message}")
            return false
        }
    }
    
    /**
     * Clear the password field
     * @return true if the field was cleared successfully, false otherwise
     */
    boolean clearPassword() {
        try {
            if (waitForElement(passwordField)) {
                Mobile.clearText(passwordField, 5)
                return true
            }
            return false
        } catch (Exception e) {
            logError("Failed to clear password field: ${e.message}")
            return false
        }
    }
    
    /**
     * Clear all fields
     * @return true if all fields were cleared successfully, false otherwise
     */
    boolean clearAllFields() {
        return clearUsername() && clearPassword()
    }
}