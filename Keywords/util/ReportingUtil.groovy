package com.katalon.framework.util

import java.text.SimpleDateFormat
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.file.StandardOpenOption

/**
 * ReportingUtil
 * 
 * This class provides methods for enhanced test reporting and logging.
 * It follows the Utility Class pattern with static methods.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only reporting and logging
 * - Open/Closed: Designed for extension through composition
 * - Liskov Substitution: Not applicable (not designed for inheritance)
 * - Interface Segregation: Provides only methods needed for reporting
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
class ReportingUtil {
    
    private static final String DEFAULT_REPORT_DIR = "Reports/custom"
    private static final String DEFAULT_SCREENSHOT_DIR = "Reports/screenshots"
    private static final String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss"
    private static final String LOG_FORMAT = "[%s] [%s] %s%n"
    
    private static String currentTestName
    private static String currentTestSuite
    private static String reportFilePath
    private static boolean initialized = false
    
    /**
     * Initialize the reporting utility
     * @param testName The name of the current test
     * @param testSuite The name of the current test suite
     * @param reportDir The directory to store reports (optional)
     */
    static void init(String testName, String testSuite, String reportDir = DEFAULT_REPORT_DIR) {
        currentTestName = testName
        currentTestSuite = testSuite
        
        // Create report directory if it doesn't exist
        File dir = new File(reportDir)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        
        // Create screenshot directory if it doesn't exist
        File screenshotDir = new File(DEFAULT_SCREENSHOT_DIR)
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs()
        }
        
        // Create report file name with timestamp
        String timestamp = new SimpleDateFormat(DATE_FORMAT).format(new Date())
        String fileName = "${testSuite}_${testName}_${timestamp}.log"
        reportFilePath = Paths.get(reportDir, fileName).toString()
        
        // Write report header
        String header = "=".multiply(80) + "\n"
        header += "Test Report: ${testName}\n"
        header += "Test Suite: ${testSuite}\n"
        header += "Started: ${new Date()}\n"
        header += "=".multiply(80) + "\n\n"
        
        Files.write(Paths.get(reportFilePath), header.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        
        initialized = true
        
        // Log initialization
        logInfo("Reporting initialized for test: ${testName}")
    }
    
    /**
     * Log an informational message
     * @param message The message to log
     */
    static void logInfo(String message) {
        log("INFO", message)
    }
    
    /**
     * Log a warning message
     * @param message The warning message to log
     */
    static void logWarning(String message) {
        log("WARNING", message)
    }
    
    /**
     * Log an error message
     * @param message The error message to log
     */
    static void logError(String message) {
        log("ERROR", message)
    }
    
    /**
     * Log a message with a specified level
     * @param level The log level
     * @param message The message to log
     */
    static void log(String level, String message) {
        // Ensure initialization
        if (!initialized) {
            init("Unknown", "Unknown")
        }
        
        // Format the log message
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())
        String formattedMessage = String.format(LOG_FORMAT, timestamp, level, message)
        
        // Write to console
        System.out.print(formattedMessage)
        
        // Write to file
        try {
            Files.write(Paths.get(reportFilePath), formattedMessage.getBytes(), StandardOpenOption.APPEND)
        } catch (Exception e) {
            System.err.println("Failed to write to report file: ${e.message}")
        }
    }
    
    /**
     * Log a step in the test
     * @param stepNumber The step number
     * @param description The step description
     */
    static void logStep(int stepNumber, String description) {
        logInfo("Step ${stepNumber}: ${description}")
    }
    
    /**
     * Log the start of a test
     * @param testName The name of the test
     */
    static void logTestStart(String testName) {
        logInfo("Starting test: ${testName}")
        logInfo("-".multiply(50))
    }
    
    /**
     * Log the end of a test
     * @param testName The name of the test
     * @param status The test status (PASSED, FAILED, etc.)
     */
    static void logTestEnd(String testName, String status) {
        logInfo("-".multiply(50))
        logInfo("Test completed: ${testName} - ${status}")
    }
    
    /**
     * Log test data
     * @param dataName The name of the data
     * @param value The data value
     */
    static void logTestData(String dataName, Object value) {
        logInfo("Test Data - ${dataName}: ${value}")
    }
    
    /**
     * Log an assertion
     * @param assertionName The name of the assertion
     * @param expected The expected value
     * @param actual The actual value
     * @param result The result of the assertion (true/false)
     */
    static void logAssertion(String assertionName, Object expected, Object actual, boolean result) {
        String status = result ? "PASSED" : "FAILED"
        String message = "Assertion: ${assertionName} - ${status}\n"
        message += "  Expected: ${expected}\n"
        message += "  Actual: ${actual}"
        
        if (result) {
            logInfo(message)
        } else {
            logError(message)
        }
    }
    
    /**
     * Log a screenshot
     * @param screenshotName The name of the screenshot
     * @param screenshotPath The path to the screenshot file
     */
    static void logScreenshot(String screenshotName, String screenshotPath) {
        logInfo("Screenshot taken: ${screenshotName} - ${screenshotPath}")
    }
    
    /**
     * Generate a summary report
     * @param testResults A map of test names to test results
     * @param outputPath The path to write the summary report
     */
    static void generateSummaryReport(Map<String, String> testResults, String outputPath) {
        StringBuilder sb = new StringBuilder()
        
        sb.append("=".multiply(80)).append("\n")
        sb.append("Test Summary Report\n")
        sb.append("Generated: ").append(new Date()).append("\n")
        sb.append("=".multiply(80)).append("\n\n")
        
        int passed = 0
        int failed = 0
        int skipped = 0
        
        sb.append("Test Results:\n")
        sb.append("-".multiply(80)).append("\n")
        sb.append(String.format("%-50s | %-10s\n", "Test Name", "Status"))
        sb.append("-".multiply(80)).append("\n")
        
        testResults.each { testName, status ->
            sb.append(String.format("%-50s | %-10s\n", testName, status))
            
            if (status == "PASSED") {
                passed++
            } else if (status == "FAILED") {
                failed++
            } else if (status == "SKIPPED") {
                skipped++
            }
        }
        
        sb.append("-".multiply(80)).append("\n\n")
        sb.append("Summary:\n")
        sb.append("  Total Tests: ").append(testResults.size()).append("\n")
        sb.append("  Passed: ").append(passed).append("\n")
        sb.append("  Failed: ").append(failed).append("\n")
        sb.append("  Skipped: ").append(skipped).append("\n")
        sb.append("  Pass Rate: ").append(String.format("%.2f%%", (passed / testResults.size()) * 100)).append("\n\n")
        
        sb.append("=".multiply(80)).append("\n")
        
        Files.write(Paths.get(outputPath), sb.toString().getBytes(), StandardOpenOption.CREATE)
        
        logInfo("Summary report generated: ${outputPath}")
    }
    
    /**
     * Get the path to the current report file
     * @return The report file path
     */
    static String getReportFilePath() {
        return reportFilePath
    }
    
    /**
     * Get a screenshot file path
     * @param screenshotName The name of the screenshot
     * @return The screenshot file path
     */
    static String getScreenshotFilePath(String screenshotName) {
        String timestamp = new SimpleDateFormat(DATE_FORMAT).format(new Date())
        return Paths.get(DEFAULT_SCREENSHOT_DIR, "${screenshotName}_${timestamp}.png").toString()
    }
}