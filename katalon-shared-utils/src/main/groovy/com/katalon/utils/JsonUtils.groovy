package com.katalon.utils

/**
 * Trait to ignore unknown properties when deserializing JSON objects
 */
trait IgnoreUnknownProperties {
    def propertyMissing(name, value) {
        // Silently ignore unknown properties
    }
}