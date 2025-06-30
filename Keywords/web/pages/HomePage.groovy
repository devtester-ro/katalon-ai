package com.katalon.framework.web.pages

import com.katalon.framework.base.BasePage
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

/**
 * HomePage
 * 
 * This class represents the home page of the application.
 * It extends BasePage and provides methods for interacting with the home page.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only home page interactions
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Can be used wherever BasePage is expected
 * - Interface Segregation: Provides only methods needed for the home page
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
class HomePage extends BasePage {
    
    // Page elements
    private final TestObject logo = findTestObject('Object Repository/Page_Home/logo')
    private final TestObject navigationMenu = findTestObject('Object Repository/Page_Home/navigation_menu')
    private final TestObject searchBox = findTestObject('Object Repository/Page_Home/search_box')
    
    /**
     * Constructor
     */
    HomePage() {
        super("Home Page")
    }
    
    /**
     * Navigate to the home page
     * @param baseUrl The base URL of the application
     * @return true if navigation was successful, false otherwise
     */
    boolean navigateTo(String baseUrl) {
        try {
            WebUI.navigateToUrl(baseUrl)
            return waitForPageLoad() && isPageLoaded()
        } catch (Exception e) {
            logError("Failed to navigate to home page: ${e.message}")
            return false
        }
    }
    
    /**
     * Verify that the page elements are present
     * @return true if all required elements are present, false otherwise
     */
    @Override
    protected boolean verifyPageElements() {
        return verifyElementPresent(logo) && 
               verifyElementPresent(navigationMenu) && 
               verifyElementPresent(searchBox)
    }
    
    /**
     * Click on the logo
     * @return true if the click was successful, false otherwise
     */
    boolean clickLogo() {
        return click(logo)
    }
    
    /**
     * Search for a term
     * @param searchTerm The term to search for
     * @return true if the search was successful, false otherwise
     */
    boolean search(String searchTerm) {
        if (!setText(searchBox, searchTerm)) {
            return false
        }
        
        // Press Enter to submit the search
        try {
            WebUI.sendKeys(searchBox, org.openqa.selenium.Keys.ENTER.toString())
            return waitForPageLoad()
        } catch (Exception e) {
            logError("Failed to submit search: ${e.message}")
            return false
        }
    }
    
    /**
     * Get the title of the page
     * @return The page title
     */
    String getPageTitle() {
        try {
            return WebUI.getWindowTitle()
        } catch (Exception e) {
            logError("Failed to get page title: ${e.message}")
            return null
        }
    }
    
    /**
     * Take a screenshot of the home page
     * @param fileName The name of the screenshot file
     * @return true if the screenshot was taken successfully, false otherwise
     */
    boolean takeHomePageScreenshot(String fileName) {
        try {
            WebUI.takeScreenshot(fileName)
            logInfo("Screenshot taken: ${fileName}")
            return true
        } catch (Exception e) {
            logError("Failed to take screenshot: ${e.message}")
            return false
        }
    }
}