package pages

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.katalon.utils.CommonUtils

/**
 * Page Object Model for Calculator page
 * This encapsulates all interactions with the calculator UI
 */
class CalculatorPage {
    
    // Page elements - these would typically be defined in Object Repository
    private static final String BUTTON_PLUS = 'Page_React Calculator/button_plus'
    private static final String BUTTON_MINUS = 'Page_React Calculator/button_minus'
    private static final String BUTTON_MULTIPLY = 'Page_React Calculator/button_multiply'
    private static final String BUTTON_DIVIDE = 'Page_React Calculator/button_divide'
    private static final String BUTTON_EQUAL = 'Page_React Calculator/button_equal'
    private static final String DISPLAY = 'Page_React Calculator/display'
    
    /**
     * Clicks a number on the calculator
     * @param number The number to click
     */
    void clickNumber(String number) {
        CommonUtils.logInfo("Clicking number: ${number}")
        // This would use CustomKeywords or direct element interaction
        // For now, using a placeholder implementation
        WebUI.comment("Clicking number: ${number}")
    }
    
    /**
     * Clicks the plus button
     */
    void clickPlus() {
        CommonUtils.logInfo("Clicking plus button")
        WebUI.click(findTestObject(BUTTON_PLUS))
    }
    
    /**
     * Clicks the minus button
     */
    void clickMinus() {
        CommonUtils.logInfo("Clicking minus button")
        WebUI.click(findTestObject(BUTTON_MINUS))
    }
    
    /**
     * Clicks the multiply button
     */
    void clickMultiply() {
        CommonUtils.logInfo("Clicking multiply button")
        WebUI.click(findTestObject(BUTTON_MULTIPLY))
    }
    
    /**
     * Clicks the divide button
     */
    void clickDivide() {
        CommonUtils.logInfo("Clicking divide button")
        WebUI.click(findTestObject(BUTTON_DIVIDE))
    }
    
    /**
     * Clicks the equals button
     */
    void clickEquals() {
        CommonUtils.logInfo("Clicking equals button")
        WebUI.click(findTestObject(BUTTON_EQUAL))
    }
    
    /**
     * Gets the current display value
     * @return The display value as a string
     */
    String getDisplayValue() {
        CommonUtils.logInfo("Getting display value")
        return WebUI.getText(findTestObject(DISPLAY))
    }
    
    /**
     * Verifies that the display shows the expected result
     * @param expectedResult The expected result
     */
    void verifyResult(String expectedResult) {
        String actualResult = getDisplayValue()
        CommonUtils.logInfo("Verifying result - Expected: ${expectedResult}, Actual: ${actualResult}")
        
        if (expectedResult != actualResult) {
            String errorMessage = "Calculator result mismatch - Expected: ${expectedResult}, Actual: ${actualResult}"
            CommonUtils.logError(errorMessage)
            throw new AssertionError(errorMessage)
        }
    }
    
    /**
     * Performs addition operation
     * @param firstNumber First number
     * @param secondNumber Second number
     * @return The result from the display
     */
    String performAddition(String firstNumber, String secondNumber) {
        clickNumber(firstNumber)
        clickPlus()
        clickNumber(secondNumber)
        clickEquals()
        return getDisplayValue()
    }
    
    /**
     * Performs subtraction operation
     * @param firstNumber First number
     * @param secondNumber Second number
     * @return The result from the display
     */
    String performSubtraction(String firstNumber, String secondNumber) {
        clickNumber(firstNumber)
        clickMinus()
        clickNumber(secondNumber)
        clickEquals()
        return getDisplayValue()
    }
    
    /**
     * Performs multiplication operation
     * @param firstNumber First number
     * @param secondNumber Second number
     * @return The result from the display
     */
    String performMultiplication(String firstNumber, String secondNumber) {
        clickNumber(firstNumber)
        clickMultiply()
        clickNumber(secondNumber)
        clickEquals()
        return getDisplayValue()
    }
    
    /**
     * Performs division operation
     * @param firstNumber First number
     * @param secondNumber Second number
     * @return The result from the display
     */
    String performDivision(String firstNumber, String secondNumber) {
        clickNumber(firstNumber)
        clickDivide()
        clickNumber(secondNumber)
        clickEquals()
        return getDisplayValue()
    }
}