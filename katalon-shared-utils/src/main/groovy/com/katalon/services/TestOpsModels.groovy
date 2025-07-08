package com.katalon.services

import com.katalon.utils.IgnoreUnknownProperties

/**
 * Data classes for TestOps integration
 */
class Project implements IgnoreUnknownProperties {
    Long id
    String name
    Long teamId
}

class Team implements IgnoreUnknownProperties {
    Long id
    String role
    String name
    Organization organization
}

class Organization implements IgnoreUnknownProperties {
    Long id
    String role
    String name
}