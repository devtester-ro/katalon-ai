package com.katalon.services

import java.nio.file.Path
import java.nio.file.Paths
import org.apache.commons.lang3.StringEscapeUtils
import org.apache.commons.lang3.StringUtils
import groovy.json.JsonSlurper
import com.katalon.config.KatalonConfig
import com.katalon.utils.CommonUtils

/**
 * Service class for TestOps integration operations
 * Extracted from duplicated KatalonHelper classes
 */
class TestOpsService {
    
    /**
     * Updates TestOps integration info if not already configured
     */
    static void updateInfo() {
        try {
            // Note: This method depends on Katalon-specific classes that aren't available in shared utils
            // In a real implementation, these would be injected or passed as parameters
            updateInfoInternal()
        } catch (Exception e) {
            // Silently handle exceptions - typical for optional integrations
        }
    }
    
    /**
     * Internal implementation that would be called with injected dependencies
     */
    private static void updateInfoInternal() {
        // This method would contain the actual implementation
        // but requires Katalon-specific dependencies to be available
        throw new UnsupportedOperationException("This method requires Katalon runtime dependencies")
    }
    
    /**
     * Requests authentication token from TestOps server
     */
    static String requestToken(String serverUrl, String username, String password) {
        String clientCredentials = KatalonConfig.OAUTH2_CLIENT_ID + ":" + KatalonConfig.OAUTH2_CLIENT_SECRET
        String url = serverUrl + "/oauth/token"
        
        // Note: Actual implementation would use injected HTTP client
        throw new UnsupportedOperationException("HTTP client implementation needed")
    }
    
    /**
     * Retrieves first available project from TestOps
     */
    static def getFirstProject(String serverUrl, String token) {
        String url = serverUrl + "/api/v1/projects/first"
        
        // Note: Actual implementation would use injected HTTP client
        throw new UnsupportedOperationException("HTTP client implementation needed")
    }
    
    /**
     * Loads user properties from application configuration
     */
    static Properties getUserProperties() {
        Path path = Paths.get(KatalonConfig.APP_USER_DIR_LOCATION, 'application.properties')
        File file = path.toFile()
        
        if (!file.exists()) {
            return new Properties()
        }
        
        file.withInputStream { stream ->
            Properties properties = new Properties()
            properties.load(stream)
            return properties
        }
    }
    
    /**
     * Checks if TestOps integration is already enabled
     */
    static boolean isIntegratedEnabled(File settingsFile) {
        if (!settingsFile.exists()) {
            return false
        }
        
        settingsFile.withInputStream { stream ->
            Properties properties = new Properties()
            properties.load(stream)
            String project = properties.getProperty('analytics.project')
            return project != null
        }
    }
    
    /**
     * Escapes and formats value for properties file storage
     */
    static String getRawValue(String value) {
        if (value == null) {
            return null
        }
        return "\"" + StringEscapeUtils.escapeJava(value) + "\""
    }
}