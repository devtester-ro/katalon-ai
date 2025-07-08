package com.katalon.di

import com.google.inject.Guice
import com.google.inject.Injector

/**
 * Dependency injection container for Katalon projects
 * This provides a centralized way to manage dependencies
 */
class DIContainer {
    
    private static Injector injector = null
    
    /**
     * Initialize the DI container with default modules
     */
    static void initialize() {
        initialize(new KatalonModule())
    }
    
    /**
     * Initialize the DI container with custom modules
     */
    static void initialize(com.google.inject.Module... modules) {
        injector = Guice.createInjector(modules)
    }
    
    /**
     * Get an instance of the specified class with dependencies injected
     */
    static <T> T getInstance(Class<T> clazz) {
        if (injector == null) {
            initialize()
        }
        return injector.getInstance(clazz)
    }
    
    /**
     * Inject dependencies into an existing object
     */
    static void injectMembers(Object object) {
        if (injector == null) {
            initialize()
        }
        injector.injectMembers(object)
    }
    
    /**
     * Reset the container (mainly for testing)
     */
    static void reset() {
        injector = null
    }
}