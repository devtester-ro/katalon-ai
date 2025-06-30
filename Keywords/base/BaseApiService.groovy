package com.katalon.framework.base

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.RestRequestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

/**
 * BaseApiService
 * 
 * This is the base class for all API service objects in the framework.
 * It provides common functionality for making API requests and handling responses.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only API interactions
 * - Open/Closed: Designed for extension through inheritance
 * - Liskov Substitution: Subclasses can be used wherever BaseApiService is expected
 * - Interface Segregation: Provides only methods needed by all API services
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
abstract class BaseApiService {
    
    protected String serviceName
    protected String baseUrl
    protected Map<String, String> defaultHeaders
    protected int defaultTimeout = 30
    protected JsonSlurper jsonSlurper
    
    /**
     * Constructor
     * @param serviceName The name of the service
     * @param baseUrl The base URL for the service
     */
    BaseApiService(String serviceName, String baseUrl) {
        this.serviceName = serviceName
        this.baseUrl = baseUrl
        this.defaultHeaders = [
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        ]
        this.jsonSlurper = new JsonSlurper()
    }
    
    /**
     * Set a default header
     * @param name The header name
     * @param value The header value
     */
    void setDefaultHeader(String name, String value) {
        defaultHeaders.put(name, value)
    }
    
    /**
     * Remove a default header
     * @param name The header name
     */
    void removeDefaultHeader(String name) {
        defaultHeaders.remove(name)
    }
    
    /**
     * Create a request object
     * @param endpoint The API endpoint
     * @param method The HTTP method (GET, POST, PUT, DELETE, etc.)
     * @param headers Additional headers (will be merged with default headers)
     * @param body The request body (for POST, PUT, etc.)
     * @return The created request object
     */
    protected RequestObject createRequest(String endpoint, String method, Map<String, String> headers = [:], Object body = null) {
        // Combine default headers with provided headers
        Map<String, String> mergedHeaders = new HashMap<>(defaultHeaders)
        mergedHeaders.putAll(headers)
        
        // Create a list of TestObjectProperty objects for headers
        List<TestObjectProperty> headerProperties = mergedHeaders.collect { key, value ->
            new TestObjectProperty(key, ConditionType.EQUALS, value)
        }
        
        // Build the full URL
        String url = baseUrl
        if (!baseUrl.endsWith('/') && !endpoint.startsWith('/')) {
            url += '/'
        }
        url += endpoint
        
        // Create the request builder
        RestRequestObjectBuilder builder = new RestRequestObjectBuilder()
            .withRestUrl(url)
            .withHttpHeaders(headerProperties)
            .withRestRequestMethod(method)
        
        // Add body if provided
        if (body != null) {
            String bodyContent = body instanceof String ? body : JsonOutput.toJson(body)
            builder = builder.withRestBody(bodyContent)
        }
        
        return builder.build()
    }
    
    /**
     * Send a GET request
     * @param endpoint The API endpoint
     * @param headers Additional headers
     * @return The response object
     */
    protected ResponseObject get(String endpoint, Map<String, String> headers = [:]) {
        logInfo("Sending GET request to ${endpoint}")
        RequestObject request = createRequest(endpoint, 'GET', headers)
        ResponseObject response = WS.sendRequest(request)
        logResponse(response)
        return response
    }
    
    /**
     * Send a POST request
     * @param endpoint The API endpoint
     * @param body The request body
     * @param headers Additional headers
     * @return The response object
     */
    protected ResponseObject post(String endpoint, Object body, Map<String, String> headers = [:]) {
        logInfo("Sending POST request to ${endpoint}")
        RequestObject request = createRequest(endpoint, 'POST', headers, body)
        ResponseObject response = WS.sendRequest(request)
        logResponse(response)
        return response
    }
    
    /**
     * Send a PUT request
     * @param endpoint The API endpoint
     * @param body The request body
     * @param headers Additional headers
     * @return The response object
     */
    protected ResponseObject put(String endpoint, Object body, Map<String, String> headers = [:]) {
        logInfo("Sending PUT request to ${endpoint}")
        RequestObject request = createRequest(endpoint, 'PUT', headers, body)
        ResponseObject response = WS.sendRequest(request)
        logResponse(response)
        return response
    }
    
    /**
     * Send a DELETE request
     * @param endpoint The API endpoint
     * @param headers Additional headers
     * @return The response object
     */
    protected ResponseObject delete(String endpoint, Map<String, String> headers = [:]) {
        logInfo("Sending DELETE request to ${endpoint}")
        RequestObject request = createRequest(endpoint, 'DELETE', headers)
        ResponseObject response = WS.sendRequest(request)
        logResponse(response)
        return response
    }
    
    /**
     * Parse a JSON response
     * @param response The response object
     * @return The parsed JSON object
     */
    protected Object parseResponse(ResponseObject response) {
        try {
            String content = response.getResponseBodyContent()
            if (content == null || content.trim().isEmpty()) {
                return null
            }
            return jsonSlurper.parseText(content)
        } catch (Exception e) {
            logError("Failed to parse response: ${e.message}")
            return null
        }
    }
    
    /**
     * Verify response status code
     * @param response The response object
     * @param expectedStatusCode The expected status code
     * @return true if the status code matches, false otherwise
     */
    protected boolean verifyStatusCode(ResponseObject response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode()
        if (actualStatusCode == expectedStatusCode) {
            logInfo("Status code verification passed: ${actualStatusCode}")
            return true
        } else {
            logError("Status code verification failed. Expected: ${expectedStatusCode}, Actual: ${actualStatusCode}")
            return false
        }
    }
    
    /**
     * Verify response contains a specific property
     * @param response The response object
     * @param propertyName The property name to check
     * @return true if the property exists, false otherwise
     */
    protected boolean verifyResponseContainsProperty(ResponseObject response, String propertyName) {
        try {
            Object jsonResponse = parseResponse(response)
            if (jsonResponse == null) {
                return false
            }
            
            boolean hasProperty = WS.verifyElementPropertyValue(response, propertyName, WS.getElementPropertyValue(response, propertyName), false)
            if (hasProperty) {
                logInfo("Response contains property: ${propertyName}")
                return true
            } else {
                logError("Response does not contain property: ${propertyName}")
                return false
            }
        } catch (Exception e) {
            logError("Failed to verify response property: ${e.message}")
            return false
        }
    }
    
    /**
     * Verify response property value
     * @param response The response object
     * @param propertyName The property name to check
     * @param expectedValue The expected value
     * @return true if the property value matches, false otherwise
     */
    protected boolean verifyPropertyValue(ResponseObject response, String propertyName, Object expectedValue) {
        try {
            Object jsonResponse = parseResponse(response)
            if (jsonResponse == null) {
                return false
            }
            
            Object actualValue = WS.getElementPropertyValue(response, propertyName)
            boolean matches = WS.verifyElementPropertyValue(response, propertyName, expectedValue, false)
            if (matches) {
                logInfo("Property ${propertyName} has expected value: ${expectedValue}")
                return true
            } else {
                logError("Property ${propertyName} value mismatch. Expected: ${expectedValue}, Actual: ${actualValue}")
                return false
            }
        } catch (Exception e) {
            logError("Failed to verify property value: ${e.message}")
            return false
        }
    }
    
    /**
     * Log response details
     * @param response The response object
     */
    protected void logResponse(ResponseObject response) {
        int statusCode = response.getStatusCode()
        String statusText = response.getResponseBodyContent()
        logInfo("Response received: Status ${statusCode}")
        logInfo("Response body: ${statusText}")
    }
    
    /**
     * Log an informational message
     * @param message The message to log
     */
    protected void logInfo(String message) {
        KeywordUtil.logInfo("[${serviceName}] ${message}")
    }
    
    /**
     * Log an error message
     * @param message The error message to log
     */
    protected void logError(String message) {
        KeywordUtil.markWarning("[${serviceName}] ERROR: ${message}")
    }
    
    /**
     * Log a warning message
     * @param message The warning message to log
     */
    protected void logWarning(String message) {
        KeywordUtil.markWarning("[${serviceName}] WARNING: ${message}")
    }
}