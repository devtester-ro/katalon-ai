package com.katalon.framework.util

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonSlurper
import java.nio.file.Paths

/**
 * ConfigurationManager
 * 
 * This class provides methods for managing configuration settings.
 * It follows the Singleton pattern to ensure only one instance exists.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only configuration management
 * - Open/Closed: Designed for extension through composition
 * - Liskov Substitution: Not applicable (not designed for inheritance)
 * - Interface Segregation: Provides only methods needed for configuration
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
class ConfigurationManager {
    
    private static ConfigurationManager instance
    private Map<String, Object> configCache = [:]
    private JsonSlurper jsonSlurper = new JsonSlurper()
    
    /**
     * Private constructor to prevent instantiation
     */
    private ConfigurationManager() {
        // Initialize with default values
        loadDefaultConfigurations()
    }
    
    /**
     * Get the singleton instance
     * @return The ConfigurationManager instance
     */
    static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager()
        }
        return instance
    }
    
    /**
     * Load default configurations
     */
    private void loadDefaultConfigurations() {
        // Load global variables from Katalon profile
        Map<String, Object> globalVars = RunConfiguration.getGlobalVariables()
        if (globalVars != null) {
            configCache.putAll(globalVars)
        }
    }
    
    /**
     * Load configuration from a JSON file
     * @param filePath The path to the JSON file
     * @return true if the file was loaded successfully, false otherwise
     */
    boolean loadConfigFromFile(String filePath) {
        try {
            File configFile = new File(filePath)
            if (!configFile.exists()) {
                KeywordUtil.markWarning("Configuration file not found: ${filePath}")
                return false
            }
            
            Object parsedConfig = jsonSlurper.parse(configFile)
            if (parsedConfig instanceof Map) {
                configCache.putAll(parsedConfig)
                KeywordUtil.logInfo("Configuration loaded from: ${filePath}")
                return true
            } else {
                KeywordUtil.markWarning("Invalid configuration format in: ${filePath}")
                return false
            }
        } catch (Exception e) {
            KeywordUtil.markWarning("Failed to load configuration: ${e.message}")
            return false
        }
    }
    
    /**
     * Get a configuration value
     * @param key The configuration key
     * @param defaultValue The default value to return if the key is not found
     * @return The configuration value, or the default value if not found
     */
    Object getConfig(String key, Object defaultValue = null) {
        if (configCache.containsKey(key)) {
            return configCache.get(key)
        }
        
        // Try to get from global variables
        Object globalValue = RunConfiguration.getGlobalVariables().get(key)
        if (globalValue != null) {
            configCache.put(key, globalValue)
            return globalValue
        }
        
        return defaultValue
    }
    
    /**
     * Set a configuration value
     * @param key The configuration key
     * @param value The configuration value
     */
    void setConfig(String key, Object value) {
        configCache.put(key, value)
    }
    
    /**
     * Check if a configuration key exists
     * @param key The configuration key
     * @return true if the key exists, false otherwise
     */
    boolean hasConfig(String key) {
        return configCache.containsKey(key) || RunConfiguration.getGlobalVariables().containsKey(key)
    }
    
    /**
     * Get all configuration values
     * @return A map of all configuration values
     */
    Map<String, Object> getAllConfigs() {
        return new HashMap<>(configCache)
    }
    
    /**
     * Get the project directory
     * @return The project directory path
     */
    String getProjectDir() {
        return RunConfiguration.getProjectDir()
    }
    
    /**
     * Get a file path relative to the project directory
     * @param relativePath The relative path
     * @return The absolute path
     */
    String getProjectFilePath(String relativePath) {
        return Paths.get(getProjectDir(), relativePath).toString()
    }
    
    /**
     * Get the current environment
     * @return The current environment (e.g., "dev", "staging", "prod")
     */
    String getEnvironment() {
        return getConfig("environment", "dev")
    }
    
    /**
     * Get the base URL for the current environment
     * @return The base URL
     */
    String getBaseUrl() {
        String env = getEnvironment()
        return getConfig("${env}.baseUrl", getConfig("baseUrl", ""))
    }
    
    /**
     * Get the API base URL for the current environment
     * @return The API base URL
     */
    String getApiBaseUrl() {
        String env = getEnvironment()
        return getConfig("${env}.apiBaseUrl", getConfig("apiBaseUrl", ""))
    }
    
    /**
     * Get the mobile app path
     * @return The mobile app path
     */
    String getMobileAppPath() {
        return getConfig("mobileAppPath", "")
    }
    
    /**
     * Get the default timeout
     * @return The default timeout in seconds
     */
    int getDefaultTimeout() {
        return getConfig("defaultTimeout", 30) as int
    }
    
    /**
     * Get the browser type
     * @return The browser type (e.g., "Chrome", "Firefox")
     */
    String getBrowserType() {
        return getConfig("browserType", "Chrome")
    }
    
    /**
     * Get the device name for mobile testing
     * @return The device name
     */
    String getDeviceName() {
        return getConfig("deviceName", "")
    }
    
    /**
     * Get the platform name for mobile testing
     * @return The platform name (e.g., "Android", "iOS")
     */
    String getPlatformName() {
        return getConfig("platformName", "Android")
    }
    
    /**
     * Get the platform version for mobile testing
     * @return The platform version
     */
    String getPlatformVersion() {
        return getConfig("platformVersion", "")
    }
    
    /**
     * Get the Appium server URL
     * @return The Appium server URL
     */
    String getAppiumServerUrl() {
        return getConfig("appiumServerUrl", "http://127.0.0.1:4723/wd/hub")
    }
}