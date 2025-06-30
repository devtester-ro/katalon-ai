import com.katalon.framework.web.WebTest
import com.katalon.framework.web.pages.HomePage
import com.katalon.framework.util.ReportingUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * HomePageTest
 * 
 * This test case verifies the functionality of the home page.
 */
class HomePageTest extends WebTest {
    
    private HomePage homePage
    
    /**
     * Constructor
     */
    HomePageTest() {
        super("Home Page Test")
    }
    
    /**
     * Execute the test
     */
    @Override
    protected void executeTest() {
        // Initialize reporting
        ReportingUtil.init(testName, "Web Tests")
        ReportingUtil.logTestStart(testName)
        
        // Initialize the home page
        homePage = new HomePage()
        
        // Test steps
        testNavigateToHomePage()
        testVerifyPageTitle()
        testSearchFunctionality()
        testClickLogo()
        
        // Log test end
        ReportingUtil.logTestEnd(testName, "PASSED")
    }
    
    /**
     * Test navigating to the home page
     */
    private void testNavigateToHomePage() {
        ReportingUtil.logStep(1, "Navigate to the home page")
        
        // Navigate to the home page
        boolean result = homePage.navigateTo(baseUrl)
        
        // Take a screenshot
        String screenshotPath = ReportingUtil.getScreenshotFilePath("home_page")
        homePage.takeHomePageScreenshot(screenshotPath)
        ReportingUtil.logScreenshot("Home Page", screenshotPath)
        
        // Verify the result
        if (!result) {
            ReportingUtil.logError("Failed to navigate to the home page")
            throw new Exception("Failed to navigate to the home page")
        }
        
        ReportingUtil.logInfo("Successfully navigated to the home page")
    }
    
    /**
     * Test verifying the page title
     */
    private void testVerifyPageTitle() {
        ReportingUtil.logStep(2, "Verify the page title")
        
        // Get the page title
        String pageTitle = homePage.getPageTitle()
        
        // Verify the page title
        boolean result = pageTitle != null && pageTitle.contains("Katalon")
        
        // Log the assertion
        ReportingUtil.logAssertion("Page Title", "Contains 'Katalon'", pageTitle, result)
        
        // Verify the result
        if (!result) {
            ReportingUtil.logError("Page title verification failed")
            throw new Exception("Page title verification failed")
        }
        
        ReportingUtil.logInfo("Page title verification passed")
    }
    
    /**
     * Test the search functionality
     */
    private void testSearchFunctionality() {
        ReportingUtil.logStep(3, "Test the search functionality")
        
        // Search for a term
        String searchTerm = "automation"
        boolean result = homePage.search(searchTerm)
        
        // Take a screenshot
        String screenshotPath = ReportingUtil.getScreenshotFilePath("search_results")
        WebUI.takeScreenshot(screenshotPath)
        ReportingUtil.logScreenshot("Search Results", screenshotPath)
        
        // Verify the result
        if (!result) {
            ReportingUtil.logError("Failed to search for '${searchTerm}'")
            throw new Exception("Failed to search for '${searchTerm}'")
        }
        
        // Verify the search results page
        String pageTitle = homePage.getPageTitle()
        boolean titleContainsSearchTerm = pageTitle != null && pageTitle.toLowerCase().contains(searchTerm)
        
        // Log the assertion
        ReportingUtil.logAssertion("Search Results Title", "Contains '${searchTerm}'", pageTitle, titleContainsSearchTerm)
        
        // Verify the result
        if (!titleContainsSearchTerm) {
            ReportingUtil.logError("Search results page title does not contain the search term")
            throw new Exception("Search results page title does not contain the search term")
        }
        
        ReportingUtil.logInfo("Search functionality works correctly")
    }
    
    /**
     * Test clicking the logo
     */
    private void testClickLogo() {
        ReportingUtil.logStep(4, "Test clicking the logo")
        
        // Click the logo
        boolean result = homePage.clickLogo()
        
        // Take a screenshot
        String screenshotPath = ReportingUtil.getScreenshotFilePath("after_logo_click")
        WebUI.takeScreenshot(screenshotPath)
        ReportingUtil.logScreenshot("After Logo Click", screenshotPath)
        
        // Verify the result
        if (!result) {
            ReportingUtil.logError("Failed to click the logo")
            throw new Exception("Failed to click the logo")
        }
        
        // Verify we're back on the home page
        boolean isHomePageLoaded = homePage.isPageLoaded()
        
        // Log the assertion
        ReportingUtil.logAssertion("Home Page Loaded", "true", isHomePageLoaded.toString(), isHomePageLoaded)
        
        // Verify the result
        if (!isHomePageLoaded) {
            ReportingUtil.logError("Not redirected to the home page after clicking the logo")
            throw new Exception("Not redirected to the home page after clicking the logo")
        }
        
        ReportingUtil.logInfo("Logo click functionality works correctly")
    }
}