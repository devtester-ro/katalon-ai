package com.katalon.config

/**
 * Centralized configuration constants for Katalon projects
 */
class KatalonConfig {
    
    // TestOps Configuration
    public static final String DEFAULT_SERVER_URL = "https://analytics.katalon.com"
    public static final String HEADER_AUTHORIZATION = "Authorization"
    public static final String HEADER_VALUE_AUTHORIZATION_PREFIX = "Bearer "
    public static final String HEADER_AUTHORIZATION_PREFIX = "Basic "
    
    // OAuth2 Configuration
    public static final String OAUTH2_CLIENT_ID = "kit_uploader"
    public static final String OAUTH2_CLIENT_SECRET = "kit_uploader"
    
    // Login Parameters
    public static final String LOGIN_PARAM_PASSWORD = "password"
    public static final String LOGIN_PARAM_USERNAME = "username"
    public static final String LOGIN_PARAM_GRANT_TYPE_NAME = "grant_type"
    public static final String LOGIN_PARAM_GRANT_TYPE_VALUE = "password"
    
    // File System Configuration
    public static final String KATALON_HOME_ENV_NAME = "KATALON_HOME"
    public static final String KATALON_HOME_DIR = System.getenv(KATALON_HOME_ENV_NAME) ?: System.getProperty("user.home")
    public static final String APP_USER_DIR_LOCATION = KATALON_HOME_DIR + File.separator + ".katalon"
}