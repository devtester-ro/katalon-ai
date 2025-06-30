package com.katalon.framework.api.services

import com.katalon.framework.base.BaseApiService
import com.kms.katalon.core.testobject.ResponseObject
import groovy.json.JsonOutput

/**
 * UserApiService
 * 
 * This class represents the User API service.
 * It extends BaseApiService and provides methods for user-related API operations.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only user API operations
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Can be used wherever BaseApiService is expected
 * - Interface Segregation: Provides only methods needed for user API operations
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
class UserApiService extends BaseApiService {
    
    private static final String USERS_ENDPOINT = "users"
    
    /**
     * Constructor
     * @param baseUrl The base URL for the API
     */
    UserApiService(String baseUrl) {
        super("User API Service", baseUrl)
    }
    
    /**
     * Get all users
     * @return The response object
     */
    ResponseObject getAllUsers() {
        logInfo("Getting all users")
        return get(USERS_ENDPOINT)
    }
    
    /**
     * Get a user by ID
     * @param userId The user ID
     * @return The response object
     */
    ResponseObject getUserById(int userId) {
        logInfo("Getting user with ID: ${userId}")
        return get("${USERS_ENDPOINT}/${userId}")
    }
    
    /**
     * Create a new user
     * @param userData The user data
     * @return The response object
     */
    ResponseObject createUser(Map<String, Object> userData) {
        logInfo("Creating new user: ${userData}")
        return post(USERS_ENDPOINT, userData)
    }
    
    /**
     * Update a user
     * @param userId The user ID
     * @param userData The user data
     * @return The response object
     */
    ResponseObject updateUser(int userId, Map<String, Object> userData) {
        logInfo("Updating user with ID: ${userId}")
        return put("${USERS_ENDPOINT}/${userId}", userData)
    }
    
    /**
     * Delete a user
     * @param userId The user ID
     * @return The response object
     */
    ResponseObject deleteUser(int userId) {
        logInfo("Deleting user with ID: ${userId}")
        return delete("${USERS_ENDPOINT}/${userId}")
    }
    
    /**
     * Verify user data
     * @param response The response object
     * @param expectedData The expected user data
     * @return true if the user data matches, false otherwise
     */
    boolean verifyUserData(ResponseObject response, Map<String, Object> expectedData) {
        logInfo("Verifying user data")
        
        // Parse the response
        Object jsonResponse = parseResponse(response)
        if (jsonResponse == null) {
            return false
        }
        
        // Verify each expected field
        boolean allMatch = true
        expectedData.each { key, expectedValue ->
            Object actualValue = null
            try {
                actualValue = jsonResponse[key]
            } catch (Exception e) {
                logError("Failed to get value for key '${key}': ${e.message}")
                allMatch = false
                return
            }
            
            if (actualValue != expectedValue) {
                logError("Value mismatch for key '${key}'. Expected: ${expectedValue}, Actual: ${actualValue}")
                allMatch = false
            } else {
                logInfo("Value match for key '${key}': ${actualValue}")
            }
        }
        
        return allMatch
    }
    
    /**
     * Generate random user data
     * @return A map of user data
     */
    Map<String, Object> generateRandomUserData() {
        return [
            name: "John Doe",
            username: "johndoe",
            email: "john.doe@example.com",
            phone: "1234567890",
            website: "johndoe.com"
        ]
    }
}