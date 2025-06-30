package com.katalon.framework.util

import java.text.SimpleDateFormat
import java.util.concurrent.ThreadLocalRandom
import java.util.Calendar
import java.util.UUID
import java.util.Random
import java.util.Collections

/**
 * DataGenerator
 * 
 * This class provides methods for generating test data.
 * It follows the Utility Class pattern with static methods.
 * 
 * Following SOLID principles:
 * - Single Responsibility: Handles only test data generation
 * - Open/Closed: Designed for extension through composition
 * - Liskov Substitution: Not applicable (not designed for inheritance)
 * - Interface Segregation: Provides only methods needed for data generation
 * - Dependency Inversion: Depends on abstractions, not concrete implementations
 */
class DataGenerator {

    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz"
    private static final String ALPHA_UPPER = ALPHA.toUpperCase()
    private static final String NUMERIC = "0123456789"
    private static final String SPECIAL_CHARS = "!@#\$%^&*()_+-=[]{}|;:,.<>?"
    private static final String ALPHA_NUMERIC = ALPHA + ALPHA_UPPER + NUMERIC
    private static final String ALL_CHARS = ALPHA_NUMERIC + SPECIAL_CHARS

    private static final String[] FIRST_NAMES = [
        "James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph", "Thomas", "Charles",
        "Mary", "Patricia", "Jennifer", "Linda", "Elizabeth", "Barbara", "Susan", "Jessica", "Sarah", "Karen"
    ]

    private static final String[] LAST_NAMES = [
        "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
        "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson"
    ]

    private static final String[] DOMAINS = [
        "example.com", "test.com", "sample.org", "demo.net", "mail.com",
        "katalon.com", "automation.org", "testing.net", "qa.com", "dev.io"
    ]

    private static final String[] STREET_NAMES = [
        "Main", "Park", "Oak", "Pine", "Maple", "Cedar", "Elm", "Washington", "Lake", "Hill"
    ]

    private static final String[] STREET_TYPES = [
        "St", "Ave", "Blvd", "Rd", "Ln", "Dr", "Way", "Pl", "Ct", "Terrace"
    ]

    private static final String[] CITIES = [
        "New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose",
        "Austin", "Jacksonville", "Fort Worth", "Columbus", "San Francisco", "Charlotte", "Indianapolis", "Seattle", "Denver", "Boston"
    ]

    private static final String[] STATES = [
        "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
        "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
        "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
        "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
        "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    ]

    /**
     * Generate a random string
     * @param length The length of the string
     * @param charSet The character set to use (default: alphanumeric)
     * @return A random string
     */
    static String randomString(int length, String charSet = ALPHA_NUMERIC) {
        StringBuilder sb = new StringBuilder()
        Random random = new Random()

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charSet.length())
            sb.append(charSet.charAt(randomIndex))
        }

        return sb.toString()
    }

    /**
     * Generate a random alphabetic string
     * @param length The length of the string
     * @return A random alphabetic string
     */
    static String randomAlpha(int length) {
        return randomString(length, ALPHA + ALPHA_UPPER)
    }

    /**
     * Generate a random numeric string
     * @param length The length of the string
     * @return A random numeric string
     */
    static String randomNumeric(int length) {
        return randomString(length, NUMERIC)
    }

    /**
     * Generate a random alphanumeric string
     * @param length The length of the string
     * @return A random alphanumeric string
     */
    static String randomAlphaNumeric(int length) {
        return randomString(length, ALPHA_NUMERIC)
    }

    /**
     * Generate a random integer
     * @param min The minimum value (inclusive)
     * @param max The maximum value (inclusive)
     * @return A random integer
     */
    static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1)
    }

    /**
     * Generate a random double
     * @param min The minimum value (inclusive)
     * @param max The maximum value (exclusive)
     * @param decimalPlaces The number of decimal places
     * @return A random double
     */
    static double randomDouble(double min, double max, int decimalPlaces = 2) {
        double random = ThreadLocalRandom.current().nextDouble(min, max)
        double scale = Math.pow(10, decimalPlaces)
        return Math.round(random * scale) / scale
    }

    /**
     * Generate a random boolean
     * @return A random boolean
     */
    static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean()
    }

    /**
     * Generate a random date
     * @param startYear The start year
     * @param endYear The end year
     * @param format The date format (default: yyyy-MM-dd)
     * @return A random date string
     */
    static String randomDate(int startYear, int endYear, String format = "yyyy-MM-dd") {
        Calendar calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, randomInt(startYear, endYear))
        calendar.set(Calendar.MONTH, randomInt(0, 11))
        calendar.set(Calendar.DAY_OF_MONTH, randomInt(1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)))

        SimpleDateFormat dateFormat = new SimpleDateFormat(format)
        return dateFormat.format(calendar.getTime())
    }

    /**
     * Generate a random future date
     * @param daysAhead The maximum number of days ahead
     * @param format The date format (default: yyyy-MM-dd)
     * @return A random future date string
     */
    static String randomFutureDate(int daysAhead, String format = "yyyy-MM-dd") {
        Calendar calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, randomInt(1, daysAhead))

        SimpleDateFormat dateFormat = new SimpleDateFormat(format)
        return dateFormat.format(calendar.getTime())
    }

    /**
     * Generate a random past date
     * @param daysBack The maximum number of days back
     * @param format The date format (default: yyyy-MM-dd)
     * @return A random past date string
     */
    static String randomPastDate(int daysBack, String format = "yyyy-MM-dd") {
        Calendar calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -randomInt(1, daysBack))

        SimpleDateFormat dateFormat = new SimpleDateFormat(format)
        return dateFormat.format(calendar.getTime())
    }

    /**
     * Generate a random name
     * @return A random full name
     */
    static String randomName() {
        return randomFirstName() + " " + randomLastName()
    }

    /**
     * Generate a random first name
     * @return A random first name
     */
    static String randomFirstName() {
        return FIRST_NAMES[randomInt(0, FIRST_NAMES.length - 1)]
    }

    /**
     * Generate a random last name
     * @return A random last name
     */
    static String randomLastName() {
        return LAST_NAMES[randomInt(0, LAST_NAMES.length - 1)]
    }

    /**
     * Generate a random email
     * @param firstName Optional first name (random if not provided)
     * @param lastName Optional last name (random if not provided)
     * @return A random email address
     */
    static String randomEmail(String firstName = null, String lastName = null) {
        firstName = firstName ?: randomFirstName().toLowerCase()
        lastName = lastName ?: randomLastName().toLowerCase()
        String domain = DOMAINS[randomInt(0, DOMAINS.length - 1)]

        return "${firstName}.${lastName}@${domain}"
    }

    /**
     * Generate a random phone number
     * @param format The format (default: (XXX) XXX-XXXX)
     * @return A random phone number
     */
    static String randomPhoneNumber(String format = "(XXX) XXX-XXXX") {
        String result = format

        for (int i = 0; i < format.length(); i++) {
            if (format.charAt(i) == 'X') {
                result = result.replaceFirst("X", randomNumeric(1))
            }
        }

        return result
    }

    /**
     * Generate a random address
     * @return A random address
     */
    static Map<String, String> randomAddress() {
        String streetNumber = randomInt(100, 9999).toString()
        String streetName = STREET_NAMES[randomInt(0, STREET_NAMES.length - 1)]
        String streetType = STREET_TYPES[randomInt(0, STREET_TYPES.length - 1)]
        String city = CITIES[randomInt(0, CITIES.length - 1)]
        String state = STATES[randomInt(0, STATES.length - 1)]
        String zipCode = randomNumeric(5)

        return [
            streetAddress: "${streetNumber} ${streetName} ${streetType}",
            city: city,
            state: state,
            zipCode: zipCode,
            fullAddress: "${streetNumber} ${streetName} ${streetType}, ${city}, ${state} ${zipCode}"
        ]
    }

    /**
     * Generate a random credit card number
     * @return A random credit card number
     */
    static String randomCreditCardNumber() {
        // This is a simplified version that generates a number that looks like a credit card
        // but is not a valid credit card number
        return "4" + randomNumeric(15)  // Starts with 4 to look like a Visa
    }

    /**
     * Generate a random username
     * @return A random username
     */
    static String randomUsername() {
        return randomFirstName().toLowerCase() + randomInt(10, 9999)
    }

    /**
     * Generate a random password
     * @param length The length of the password
     * @param includeSpecial Whether to include special characters
     * @return A random password
     */
    static String randomPassword(int length = 12, boolean includeSpecial = true) {
        String charSet = includeSpecial ? ALL_CHARS : ALPHA_NUMERIC
        return randomString(length, charSet)
    }

    /**
     * Generate a random UUID
     * @return A random UUID
     */
    static String randomUUID() {
        return UUID.randomUUID().toString()
    }

    /**
     * Generate a random item from a list
     * @param items The list of items
     * @return A random item from the list
     */
    static <T> T randomItem(List<T> items) {
        if (items == null || items.isEmpty()) {
            return null
        }
        return items[randomInt(0, items.size() - 1)]
    }

    /**
     * Generate a random subset of a list
     * @param items The list of items
     * @param count The number of items to select
     * @return A random subset of the list
     */
    static <T> List<T> randomSubset(List<T> items, int count) {
        if (items == null || items.isEmpty() || count <= 0) {
            return []
        }

        count = Math.min(count, items.size())
        List<T> copy = new ArrayList<>(items)
        Collections.shuffle(copy)
        return copy.subList(0, count)
    }

    /**
     * Log generated test data
     * @param dataType The type of data
     * @param value The generated value
     */
    static void logGeneratedData(String dataType, Object value) {
        System.out.println("Generated test data - ${dataType}: ${value}")
    }
}
