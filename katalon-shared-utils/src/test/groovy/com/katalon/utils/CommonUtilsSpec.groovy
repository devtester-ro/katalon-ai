package com.katalon.utils

import spock.lang.Specification

/**
 * Unit tests for CommonUtils
 */
class CommonUtilsSpec extends Specification {

    def "isNotBlank should return true for non-empty strings"() {
        when:
        def result = CommonUtils.isNotBlank("test")
        
        then:
        result == true
    }

    def "isNotBlank should return false for blank strings"() {
        expect:
        CommonUtils.isNotBlank(input) == expected
        
        where:
        input    | expected
        null     | false
        ""       | false
        "   "    | false
        "test"   | true
        " test " | true
    }

    def "isBlank should return true for blank strings"() {
        expect:
        CommonUtils.isBlank(input) == expected
        
        where:
        input    | expected
        null     | true
        ""       | true
        "   "    | true
        "test"   | false
        " test " | false
    }

    def "getProperty should return default value when property is missing"() {
        given:
        Properties props = new Properties()
        
        when:
        def result = CommonUtils.getProperty(props, "missing.key", "default")
        
        then:
        result == "default"
    }

    def "getProperty should return actual value when property exists"() {
        given:
        Properties props = new Properties()
        props.setProperty("test.key", "test.value")
        
        when:
        def result = CommonUtils.getProperty(props, "test.key", "default")
        
        then:
        result == "test.value"
    }

    def "ensureDirectoryExists should create directory when it doesn't exist"() {
        given:
        def tempDir = File.createTempDir("test", "dir")
        def testDir = new File(tempDir, "subdir")
        
        // Ensure it doesn't exist
        if (testDir.exists()) {
            testDir.delete()
        }
        
        when:
        def result = CommonUtils.ensureDirectoryExists(testDir.absolutePath)
        
        then:
        result == true
        testDir.exists()
        
        cleanup:
        testDir.deleteDir()
        tempDir.deleteDir()
    }
}