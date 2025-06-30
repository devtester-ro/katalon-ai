import com.katalon.framework.mobile.MobileTest
import com.katalon.framework.mobile.screens.LoginScreen
import com.katalon.framework.util.ReportingUtil
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

/**
 * LoginTest
 * 
 * This test case verifies the login functionality of the mobile application.
 */
class LoginTest extends MobileTest {
    
    private LoginScreen loginScreen
    
    /**
     * Constructor
     */
    LoginTest() {
        super("Login Test")
    }
    
    /**
     * Execute the test
     */
    @Override
    protected void executeTest() {
        // Initialize reporting
        ReportingUtil.init(testName, "Mobile Tests")
        ReportingUtil.logTestStart(testName)
        
        // Initialize the login screen
        loginScreen = new LoginScreen()
        
        // Test steps
        testVerifyLoginScreen()
        testInvalidLogin()
        testValidLogin()
        
        // Log test end
        ReportingUtil.logTestEnd(testName, "PASSED")
    }
    
    /**
     * Test verifying the login screen
     */
    private void testVerifyLoginScreen() {
        ReportingUtil.logStep(1, "Verify the login screen is displayed")
        
        // Verify the login screen is loaded
        boolean isScreenLoaded = loginScreen.isScreenLoaded()
        
        // Take a screenshot
        String screenshotPath = ReportingUtil.getScreenshotFilePath("login_screen")
        loginScreen.takeLoginScreenshot(screenshotPath)
        ReportingUtil.logScreenshot("Login Screen", screenshotPath)
        
        // Log the assertion
        ReportingUtil.logAssertion("Login Screen Loaded", "true", isScreenLoaded.toString(), isScreenLoaded)
        
        // Verify the result
        if (!isScreenLoaded) {
            ReportingUtil.logError("Login screen is not displayed")
            throw new Exception("Login screen is not displayed")
        }
        
        ReportingUtil.logInfo("Login screen is displayed correctly")
    }
    
    /**
     * Test invalid login
     */
    private void testInvalidLogin() {
        ReportingUtil.logStep(2, "Test invalid login")
        
        // Clear any existing text
        loginScreen.clearAllFields()
        
        // Enter invalid credentials
        String invalidUsername = "invalid_user"
        String invalidPassword = "invalid_password"
        ReportingUtil.logTestData("Invalid Username", invalidUsername)
        ReportingUtil.logTestData("Invalid Password", invalidPassword)
        
        // Attempt to login
        boolean loginAttempted = loginScreen.login(invalidUsername, invalidPassword)
        
        // Take a screenshot
        String screenshotPath = ReportingUtil.getScreenshotFilePath("invalid_login_result")
        loginScreen.takeLoginScreenshot(screenshotPath)
        ReportingUtil.logScreenshot("Invalid Login Result", screenshotPath)
        
        // Verify the login attempt
        ReportingUtil.logAssertion("Login Attempted", "true", loginAttempted.toString(), loginAttempted)
        
        // Verify the result
        if (!loginAttempted) {
            ReportingUtil.logError("Failed to attempt login with invalid credentials")
            throw new Exception("Failed to attempt login with invalid credentials")
        }
        
        // Verify that we're still on the login screen (login failed)
        boolean stillOnLoginScreen = loginScreen.isScreenLoaded()
        ReportingUtil.logAssertion("Still On Login Screen", "true", stillOnLoginScreen.toString(), stillOnLoginScreen)
        
        if (!stillOnLoginScreen) {
            ReportingUtil.logError("Invalid login was successful, which is unexpected")
            throw new Exception("Invalid login was successful, which is unexpected")
        }
        
        ReportingUtil.logInfo("Invalid login test passed - login was rejected as expected")
    }
    
    /**
     * Test valid login
     */
    private void testValidLogin() {
        ReportingUtil.logStep(3, "Test valid login")
        
        // Clear any existing text
        loginScreen.clearAllFields()
        
        // Enter valid credentials
        String validUsername = "test_user"
        String validPassword = "test_password"
        ReportingUtil.logTestData("Valid Username", validUsername)
        ReportingUtil.logTestData("Valid Password", validPassword)
        
        // Attempt to login
        boolean loginAttempted = loginScreen.login(validUsername, validPassword)
        
        // Take a screenshot
        String screenshotPath = ReportingUtil.getScreenshotFilePath("valid_login_result")
        Mobile.takeScreenshot(screenshotPath)
        ReportingUtil.logScreenshot("Valid Login Result", screenshotPath)
        
        // Verify the login attempt
        ReportingUtil.logAssertion("Login Attempted", "true", loginAttempted.toString(), loginAttempted)
        
        // Verify the result
        if (!loginAttempted) {
            ReportingUtil.logError("Failed to attempt login with valid credentials")
            throw new Exception("Failed to attempt login with valid credentials")
        }
        
        // Verify that we're no longer on the login screen (login succeeded)
        boolean stillOnLoginScreen = loginScreen.isScreenLoaded()
        ReportingUtil.logAssertion("No Longer On Login Screen", "false", stillOnLoginScreen.toString(), !stillOnLoginScreen)
        
        if (stillOnLoginScreen) {
            ReportingUtil.logError("Valid login failed, still on login screen")
            throw new Exception("Valid login failed, still on login screen")
        }
        
        ReportingUtil.logInfo("Valid login test passed - login was successful")
    }
}