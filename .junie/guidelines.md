# Katalon AI Project Guidelines

This document provides guidelines and instructions for developing and maintaining the Katalon AI project, which integrates Katalon Studio with AI-powered tools like Junie the AI and GitHub Copilot.

## Build/Configuration Instructions

### Setting Up the Project

1. **Prerequisites**:
   - Katalon Studio (latest version recommended)
   - Java JDK 8 or higher
   - Git
   - GitHub account (for GitHub Copilot integration)

2. **Clone and Open the Project**:
   ```bash
   git clone https://github.com/your-username/katalon-ai.git
   ```
   - Open Katalon Studio
   - Click "File" > "Open Project" and select the cloned repository

3. **Configure Project Settings**:
   - Go to "Project" > "Settings"
   - Under "Execution":
     - Set default timeout to 30 seconds
     - Configure desired browser for WebUI tests
     - Configure desired device for Mobile tests
   - Under "Desired Capabilities":
     - Configure WebDriver capabilities for specific browsers
     - Configure Appium capabilities for mobile testing

4. **Configure Profiles**:
   - The project includes a default profile with global variables
   - Create environment-specific profiles (dev, staging, production) by copying the default profile
   - Modify the variables in each profile to match the environment

5. **Install Required Plugins**:
   - Go to "Katalon Studio" > "Plugins" > "Install Plugins"
   - Install the following plugins:
     - WebDriver
     - Appium
     - TestNG
     - Git
     - AI-powered Test Generation (if available)

## Testing Information

### Test Structure

The project follows a standard Katalon Studio structure:
- **Test Cases**: Individual test scripts
- **Test Suites**: Collections of test cases
- **Test Listeners**: Hooks for test execution events
- **Object Repository**: UI and mobile element definitions
- **Keywords**: Reusable functions and utilities
- **Profiles**: Environment-specific configurations
- **Scripts**: Custom scripts and utilities

### Running Tests

#### Running Individual Test Cases

1. Navigate to the "Test Cases" folder in the Test Explorer
2. Right-click on a test case (e.g., SampleUITest)
3. Select "Run" or "Debug"
4. View results in the Log Viewer

#### Running Test Suites

1. Navigate to the "Test Suites" folder in the Test Explorer
2. Right-click on a test suite (e.g., SampleTestSuite)
3. Select "Run" or "Debug"
4. Select the desired execution profile
5. View results in the Log Viewer and Reports

#### Running from Command Line

You can run tests from the command line using the Katalon Runtime Engine:

```bash
katalonc -noSplash -runMode=console -projectPath="path/to/katalon-ai.prj" -retry=0 -testSuitePath="Test Suites/SampleTestSuite" -executionProfile="default" -browserType="Chrome"
```

### Creating New Tests

#### Creating UI Tests

1. Right-click on the "Test Cases" folder and select "New" > "Test Case"
2. Name your test case following the convention: `[Feature][Action]Test` (e.g., `LoginValidationTest`)
3. Use the WebUI keywords for browser automation:

```groovy
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Open browser and navigate to URL
WebUI.openBrowser('')
WebUI.navigateToUrl('https://example.com')

// Interact with elements
WebUI.setText(findTestObject('Object Repository/Page_Example/username_field'), 'test_user')
WebUI.click(findTestObject('Object Repository/Page_Example/login_button'))

// Verify elements or conditions
WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Example/welcome_message'), 10)

// Close browser
WebUI.closeBrowser()
```

#### Creating Mobile Tests

1. Right-click on the "Test Cases" folder and select "New" > "Test Case"
2. Name your test case following the convention: `Mobile[Feature][Action]Test` (e.g., `MobileLoginValidationTest`)
3. Use the Mobile keywords for app automation:

```groovy
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

// Start the application
Mobile.startApplication('path/to/your/app.apk', true)

// Interact with elements
Mobile.tap(findTestObject('Object Repository/Mobile/App/login_button'), 10)
Mobile.setText(findTestObject('Object Repository/Mobile/App/username_field'), 'test_user', 10)

// Verify elements or conditions
Mobile.verifyElementExist(findTestObject('Object Repository/Mobile/App/welcome_message'), 10)

// Close the application
Mobile.closeApplication()
```

#### Creating API Tests

1. Right-click on the "Test Cases" folder and select "New" > "Test Case"
2. Name your test case following the convention: `API[Feature][Action]Test` (e.g., `APIUserCreationTest`)
3. Use the WS keywords for API testing:

```groovy
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder

// Create a request object
RequestObject requestObject = new RestRequestObjectBuilder()
    .withRestUrl('https://api.example.com/users')
    .withHttpHeaders([new TestObjectProperty('Content-Type', ConditionType.EQUALS, 'application/json')])
    .withRestRequestMethod('POST')
    .withRestBody('{"name": "John Doe", "email": "john@example.com"}')
    .build()

// Send the request and get the response
ResponseObject responseObject = WS.sendRequest(requestObject)

// Verify the response
WS.verifyResponseStatusCode(responseObject, 201)
WS.verifyElementPropertyValue(responseObject, 'success', true)
```

#### Creating Test Objects

1. Right-click on the "Object Repository" folder and select "New" > "Test Object"
2. Name your test object following the convention: `Page_[PageName]/[element_name]` for UI or `Mobile/App/[element_name]` for mobile
3. Define selectors using one or more of the following strategies:
   - XPath
   - CSS
   - ID
   - Name
   - Tag
   - Class
   - Attributes

### Using AI for Test Creation

#### Using Junie the AI

1. Open the Junie AI panel in Katalon Studio
2. Describe the test scenario you want to create
3. Review and refine the generated test script
4. Save the script to the appropriate location

#### Using GitHub Copilot

1. Install the GitHub Copilot extension for your IDE
2. Start writing a test case or comment describing what you want to test
3. GitHub Copilot will suggest code completions
4. Accept or modify the suggestions as needed

## Additional Development Information

### Code Style Guidelines

1. **Naming Conventions**:
   - Test Cases: `[Feature][Action]Test` (e.g., `LoginValidationTest`)
   - Test Objects: `Page_[PageName]/[element_name]` or `Mobile/App/[element_name]`
   - Test Suites: `[Feature]TestSuite` or `[Module]TestSuite`
   - Variables: camelCase (e.g., `userName`, `loginButton`)
   - Constants: UPPER_SNAKE_CASE (e.g., `DEFAULT_TIMEOUT`, `API_BASE_URL`)

2. **Documentation**:
   - Add descriptive comments to all test cases
   - Document the purpose of each test suite
   - Add detailed descriptions to test objects
   - Document all custom keywords and functions

3. **Error Handling**:
   - Use try-catch blocks for error-prone operations
   - Use the `FailureHandling` parameter for keywords that support it
   - Log meaningful error messages

### Best Practices

1. **Test Organization**:
   - Group related test cases in the same folder
   - Create separate test suites for different features or modules
   - Use test listeners for common setup and teardown operations

2. **Test Data Management**:
   - Store test data in external files (Excel, CSV, JSON)
   - Use data-driven testing for scenarios with multiple data sets
   - Avoid hardcoding test data in scripts

3. **Object Repository Management**:
   - Organize objects by page or screen
   - Use descriptive names for objects
   - Include multiple selector strategies for robustness

4. **Custom Keywords**:
   - Create reusable functions for common operations
   - Document parameters and return values
   - Handle errors gracefully

5. **Version Control**:
   - Commit changes frequently
   - Write meaningful commit messages
   - Use branches for new features or bug fixes

### Debugging Tips

1. **Logging**:
   - Use `KeywordUtil.logInfo()` for informational messages
   - Use `KeywordUtil.markWarning()` for warnings
   - Use `KeywordUtil.markFailed()` for critical failures

2. **Screenshots**:
   - Take screenshots at key points in the test
   - Use the `takeFullPageScreenshot()` custom keyword for full page captures
   - Screenshots are automatically saved on test failure

3. **Execution Logs**:
   - Review the Log Viewer for detailed execution information
   - Check the console for system-level messages
   - Use the Reports feature for test execution summaries

### Integration with CI/CD

1. **Jenkins Integration**:
   - Use the Katalon plugin for Jenkins
   - Configure a Jenkins job to run tests on schedule or on commit
   - Publish test results and reports

2. **GitHub Actions**:
   - Create a workflow file in `.github/workflows`
   - Configure the workflow to run tests on push or pull request
   - Use the Katalon Runtime Engine Docker image

3. **Test Reports**:
   - Configure report generation in project settings
   - Publish reports to a central location
   - Integrate with test management tools if needed

### Troubleshooting Common Issues

1. **Element Not Found**:
   - Increase the timeout value
   - Check if the selector is correct
   - Use the `waitForElementPresent` keyword
   - Try alternative selector strategies

2. **Test Execution Failures**:
   - Check the execution logs for errors
   - Verify that the application is in the expected state
   - Check for environment-specific issues
   - Verify that test data is correct

3. **Mobile Testing Issues**:
   - Verify that the Appium server is running
   - Check device connectivity
   - Verify app installation
   - Check for permission issues