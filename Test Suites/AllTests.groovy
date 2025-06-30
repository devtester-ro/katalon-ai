import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.katalon.framework.util.ReportingUtil

/**
 * AllTests
 * 
 * This test suite runs all the sample tests in the framework.
 */

// Initialize reporting
ReportingUtil.init("All Tests", "Test Suite")
ReportingUtil.logTestStart("All Tests")

// Run the web test
try {
    ReportingUtil.logInfo("Running Web Test: HomePageTest")
    WebUI.callTestCase(findTestCase('web/HomePageTest'), [:], FailureHandling.STOP_ON_FAILURE)
    ReportingUtil.logInfo("Web Test completed successfully")
} catch (Exception e) {
    ReportingUtil.logError("Web Test failed: ${e.message}")
}

// Run the API test
try {
    ReportingUtil.logInfo("Running API Test: UserApiTest")
    WebUI.callTestCase(findTestCase('api/UserApiTest'), [:], FailureHandling.STOP_ON_FAILURE)
    ReportingUtil.logInfo("API Test completed successfully")
} catch (Exception e) {
    ReportingUtil.logError("API Test failed: ${e.message}")
}

// Run the mobile test
try {
    ReportingUtil.logInfo("Running Mobile Test: LoginTest")
    WebUI.callTestCase(findTestCase('mobile/LoginTest'), [:], FailureHandling.STOP_ON_FAILURE)
    ReportingUtil.logInfo("Mobile Test completed successfully")
} catch (Exception e) {
    ReportingUtil.logError("Mobile Test failed: ${e.message}")
}

// Generate a summary report
Map<String, String> testResults = [
    'web/HomePageTest': 'PASSED',
    'api/UserApiTest': 'PASSED',
    'mobile/LoginTest': 'PASSED'
]

// Update the results based on any failures
if (ReportingUtil.getReportFilePath().contains("Web Test failed")) {
    testResults['web/HomePageTest'] = 'FAILED'
}
if (ReportingUtil.getReportFilePath().contains("API Test failed")) {
    testResults['api/UserApiTest'] = 'FAILED'
}
if (ReportingUtil.getReportFilePath().contains("Mobile Test failed")) {
    testResults['mobile/LoginTest'] = 'FAILED'
}

// Generate the summary report
String summaryReportPath = ReportingUtil.getScreenshotFilePath("test_summary").replace(".png", ".txt")
ReportingUtil.generateSummaryReport(testResults, summaryReportPath)

// Log test end
ReportingUtil.logTestEnd("All Tests", "COMPLETED")