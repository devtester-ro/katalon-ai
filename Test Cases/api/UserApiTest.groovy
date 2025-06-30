import com.katalon.framework.api.ApiTest
import com.katalon.framework.api.services.UserApiService
import com.katalon.framework.util.ReportingUtil
import com.kms.katalon.core.testobject.ResponseObject

/**
 * UserApiTest
 * 
 * This test case verifies the functionality of the User API.
 */
class UserApiTest extends ApiTest {
    
    private UserApiService userApiService
    
    /**
     * Constructor
     */
    UserApiTest() {
        super("User API Test")
    }
    
    /**
     * Execute the test
     */
    @Override
    protected void executeTest() {
        // Initialize reporting
        ReportingUtil.init(testName, "API Tests")
        ReportingUtil.logTestStart(testName)
        
        // Initialize the user API service
        userApiService = new UserApiService(apiBaseUrl)
        
        // Test steps
        testGetAllUsers()
        testGetUserById()
        testCreateUser()
        testUpdateUser()
        testDeleteUser()
        
        // Log test end
        ReportingUtil.logTestEnd(testName, "PASSED")
    }
    
    /**
     * Test getting all users
     */
    private void testGetAllUsers() {
        ReportingUtil.logStep(1, "Get all users")
        
        // Get all users
        ResponseObject response = userApiService.getAllUsers()
        
        // Log the response
        logResponse(response)
        
        // Verify the status code
        boolean statusCodeValid = verifyStatusCode(response, 200)
        ReportingUtil.logAssertion("Status Code", "200", response.getStatusCode().toString(), statusCodeValid)
        
        // Verify the response contains users
        boolean containsUsers = verifyResponseContainsProperty(response, "[0]")
        ReportingUtil.logAssertion("Contains Users", "true", containsUsers.toString(), containsUsers)
        
        // Verify the result
        if (!statusCodeValid || !containsUsers) {
            ReportingUtil.logError("Failed to get all users")
            throw new Exception("Failed to get all users")
        }
        
        ReportingUtil.logInfo("Successfully retrieved all users")
    }
    
    /**
     * Test getting a user by ID
     */
    private void testGetUserById() {
        ReportingUtil.logStep(2, "Get user by ID")
        
        // Get user by ID
        int userId = 1
        ResponseObject response = userApiService.getUserById(userId)
        
        // Log the response
        logResponse(response)
        
        // Verify the status code
        boolean statusCodeValid = verifyStatusCode(response, 200)
        ReportingUtil.logAssertion("Status Code", "200", response.getStatusCode().toString(), statusCodeValid)
        
        // Verify the user ID
        boolean userIdValid = verifyPropertyValue(response, "id", userId)
        ReportingUtil.logAssertion("User ID", userId.toString(), WS.getElementPropertyValue(response, "id").toString(), userIdValid)
        
        // Verify the result
        if (!statusCodeValid || !userIdValid) {
            ReportingUtil.logError("Failed to get user by ID")
            throw new Exception("Failed to get user by ID")
        }
        
        ReportingUtil.logInfo("Successfully retrieved user by ID")
    }
    
    /**
     * Test creating a user
     */
    private void testCreateUser() {
        ReportingUtil.logStep(3, "Create a new user")
        
        // Generate user data
        Map<String, Object> userData = userApiService.generateRandomUserData()
        ReportingUtil.logTestData("User Data", userData)
        
        // Create a new user
        ResponseObject response = userApiService.createUser(userData)
        
        // Log the response
        logResponse(response)
        
        // Verify the status code
        boolean statusCodeValid = verifyStatusCode(response, 201)
        ReportingUtil.logAssertion("Status Code", "201", response.getStatusCode().toString(), statusCodeValid)
        
        // Verify the user data
        boolean userDataValid = userApiService.verifyUserData(response, userData)
        ReportingUtil.logAssertion("User Data", "Valid", userDataValid.toString(), userDataValid)
        
        // Verify the result
        if (!statusCodeValid || !userDataValid) {
            ReportingUtil.logError("Failed to create a new user")
            throw new Exception("Failed to create a new user")
        }
        
        // Store the user ID for later tests
        int userId = WS.getElementPropertyValue(response, "id") as int
        setTestData("userId", userId)
        
        ReportingUtil.logInfo("Successfully created a new user with ID: ${userId}")
    }
    
    /**
     * Test updating a user
     */
    private void testUpdateUser() {
        ReportingUtil.logStep(4, "Update a user")
        
        // Get the user ID from test data
        int userId = getTestData("userId") as int
        
        // Generate updated user data
        Map<String, Object> updatedUserData = [
            name: "Jane Doe",
            username: "janedoe",
            email: "jane.doe@example.com",
            phone: "0987654321",
            website: "janedoe.com"
        ]
        ReportingUtil.logTestData("Updated User Data", updatedUserData)
        
        // Update the user
        ResponseObject response = userApiService.updateUser(userId, updatedUserData)
        
        // Log the response
        logResponse(response)
        
        // Verify the status code
        boolean statusCodeValid = verifyStatusCode(response, 200)
        ReportingUtil.logAssertion("Status Code", "200", response.getStatusCode().toString(), statusCodeValid)
        
        // Verify the user data
        boolean userDataValid = userApiService.verifyUserData(response, updatedUserData)
        ReportingUtil.logAssertion("Updated User Data", "Valid", userDataValid.toString(), userDataValid)
        
        // Verify the result
        if (!statusCodeValid || !userDataValid) {
            ReportingUtil.logError("Failed to update the user")
            throw new Exception("Failed to update the user")
        }
        
        ReportingUtil.logInfo("Successfully updated the user")
    }
    
    /**
     * Test deleting a user
     */
    private void testDeleteUser() {
        ReportingUtil.logStep(5, "Delete a user")
        
        // Get the user ID from test data
        int userId = getTestData("userId") as int
        
        // Delete the user
        ResponseObject response = userApiService.deleteUser(userId)
        
        // Log the response
        logResponse(response)
        
        // Verify the status code
        boolean statusCodeValid = verifyStatusCode(response, 200)
        ReportingUtil.logAssertion("Status Code", "200", response.getStatusCode().toString(), statusCodeValid)
        
        // Verify the result
        if (!statusCodeValid) {
            ReportingUtil.logError("Failed to delete the user")
            throw new Exception("Failed to delete the user")
        }
        
        ReportingUtil.logInfo("Successfully deleted the user")
    }
}