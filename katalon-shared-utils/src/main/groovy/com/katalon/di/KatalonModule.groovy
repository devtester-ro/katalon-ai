package com.katalon.di

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.katalon.services.TestOpsService
import com.katalon.utils.CommonUtils

/**
 * Guice module for dependency injection configuration
 * This centralizes the configuration of all injectable services
 */
class KatalonModule extends AbstractModule {

    @Override
    protected void configure() {
        // Bind services as singletons
        bind(TestOpsService).in(Singleton)
        bind(CommonUtils).in(Singleton)
    }

    /**
     * Provides a configured HTTP client for TestOps operations
     */
    @Provides
    @Singleton
    HttpClientService provideHttpClientService() {
        return new HttpClientServiceImpl()
    }

    /**
     * Provides configuration properties
     */
    @Provides
    @Singleton
    Properties provideConfiguration() {
        Properties props = new Properties()
        
        // Load from system properties and environment variables
        System.getProperties().each { key, value ->
            if (key.toString().startsWith("katalon.")) {
                props.setProperty(key.toString(), value.toString())
            }
        }
        
        // Add environment variables with KATALON_ prefix
        System.getenv().each { key, value ->
            if (key.startsWith("KATALON_")) {
                String propKey = key.toLowerCase().replace("_", ".")
                props.setProperty(propKey, value)
            }
        }
        
        return props
    }
}

/**
 * Interface for HTTP client operations
 */
interface HttpClientService {
    String get(String url, Map<String, String> headers)
    String post(String url, String body, Map<String, String> headers)
}

/**
 * Implementation of HTTP client service
 */
class HttpClientServiceImpl implements HttpClientService {
    
    @Override
    String get(String url, Map<String, String> headers) {
        CommonUtils.logInfo("HTTP GET: ${url}")
        // Implementation would use Apache HttpClient
        throw new UnsupportedOperationException("HTTP GET implementation needed")
    }
    
    @Override
    String post(String url, String body, Map<String, String> headers) {
        CommonUtils.logInfo("HTTP POST: ${url}")
        // Implementation would use Apache HttpClient
        throw new UnsupportedOperationException("HTTP POST implementation needed")
    }
}