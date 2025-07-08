package com.katalon.utils

import org.apache.commons.lang3.StringUtils

/**
 * Common utility methods for logging, assertions, and general operations
 */
class CommonUtils {
    
    /**
     * Safe string check - returns true if string is not null, empty or whitespace
     */
    static boolean isNotBlank(String str) {
        return StringUtils.isNotBlank(str)
    }
    
    /**
     * Safe string check - returns true if string is null, empty or whitespace
     */
    static boolean isBlank(String str) {
        return StringUtils.isBlank(str)
    }
    
    /**
     * Logs an error message safely
     */
    static void logError(String message, Throwable throwable = null) {
        if (throwable) {
            System.err.println("ERROR: ${message} - ${throwable.message}")
        } else {
            System.err.println("ERROR: ${message}")
        }
    }
    
    /**
     * Logs an info message safely
     */
    static void logInfo(String message) {
        System.out.println("INFO: ${message}")
    }
    
    /**
     * Safe property retrieval with default value
     */
    static String getProperty(Properties properties, String key, String defaultValue = "") {
        return properties?.getProperty(key, defaultValue) ?: defaultValue
    }
    
    /**
     * Creates directory if it doesn't exist
     */
    static boolean ensureDirectoryExists(String directoryPath) {
        try {
            File dir = new File(directoryPath)
            if (!dir.exists()) {
                return dir.mkdirs()
            }
            return true
        } catch (Exception e) {
            logError("Failed to create directory: ${directoryPath}", e)
            return false
        }
    }
}