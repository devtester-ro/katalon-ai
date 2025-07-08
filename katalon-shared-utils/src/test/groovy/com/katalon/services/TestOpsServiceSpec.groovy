package com.katalon.services

import spock.lang.Specification

/**
 * Unit tests for TestOpsService
 */
class TestOpsServiceSpec extends Specification {

    def "getUserProperties should return empty properties when file doesn't exist"() {
        when:
        def result = TestOpsService.getUserProperties()
        
        then:
        result instanceof Properties
        result.isEmpty()
    }

    def "isIntegratedEnabled should return false when file doesn't exist"() {
        given:
        def nonExistentFile = new File("/tmp/non-existent-file.properties")
        
        when:
        def result = TestOpsService.isIntegratedEnabled(nonExistentFile)
        
        then:
        result == false
    }

    def "isIntegratedEnabled should return true when analytics.project property exists"() {
        given:
        def tempFile = File.createTempFile("test", ".properties")
        tempFile.text = "analytics.project=test-project"
        
        when:
        def result = TestOpsService.isIntegratedEnabled(tempFile)
        
        then:
        result == true
        
        cleanup:
        tempFile.delete()
    }

    def "isIntegratedEnabled should return false when analytics.project property is missing"() {
        given:
        def tempFile = File.createTempFile("test", ".properties")
        tempFile.text = "other.property=some-value"
        
        when:
        def result = TestOpsService.isIntegratedEnabled(tempFile)
        
        then:
        result == false
        
        cleanup:
        tempFile.delete()
    }

    def "getRawValue should properly escape and quote strings"() {
        expect:
        TestOpsService.getRawValue(input) == expected
        
        where:
        input              | expected
        null               | null
        "simple"           | '"simple"'
        "with spaces"      | '"with spaces"'
        'with "quotes"'    | '"with \\"quotes\\""'
        "with\nnewline"    | '"with\\nnewline"'
    }
}