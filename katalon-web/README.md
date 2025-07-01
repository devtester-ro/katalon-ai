# Katalon Web - Calculator BDD Testing Samples

> **Note:** This project is part of the [Katalon AI](../README.md) unified testing framework.

## Overview

This project demonstrates web UI testing with a Behavior-Driven Development (BDD) approach for a Calculator application. It showcases how to implement BDD testing using Katalon Studio with Cucumber integration.

See the official documentation at: https://docs.katalon.com/katalon-studio/docs/bdd-samples.html

## Features

- **BDD Testing**: Uses Cucumber for behavior-driven development
- **Page Object Model**: Implements the Page Object pattern for maintainable UI tests
- **Test Suites**: Organized test suites for different calculator operations
- **Data-Driven Testing**: Examples of parameterized tests for calculator operations

## Project Structure

```
katalon-web/
├── Object Repository/           # UI element definitions
├── Scripts/                     # Test implementation scripts
│   ├── common/                  # Common utility functions
│   └── operations/              # Calculator operation tests
├── Test Cases/                  # Test case definitions
├── Test Suites/                 # Test suite configurations
├── Include/features/            # BDD feature files
├── Include/scripts/groovy/      # Step definitions
├── build.gradle                 # Gradle build configuration
└── README.md                    # This file
```

## Getting Started

### Prerequisites

- Katalon Studio 10.2.0 or later
- Java 8 or later
- Web browser (Chrome, Firefox, etc.)

### Running Tests

#### Via Katalon Studio UI
1. Open the project in Katalon Studio
2. Navigate to Test Cases
3. Right-click on a test case and select "Run"

#### Via Command Line
```bash
../gradlew katalonRunTest -Dtestcase="operations/Verify Addition"
```

## Companion Products

### Katalon TestOps

[Katalon TestOps](https://analytics.katalon.com) is a web-based application that provides dynamic perspectives and an insightful look at your automation testing data. You can leverage your automation testing data by transforming and visualizing your data; analyzing test results; seamlessly integrating with such tools as Katalon Studio and Jira; maximizing the testing capacity with remote execution.

* Read our [documentation](https://docs.katalon.com/katalon-analytics/docs/overview.html).
* Ask a question on [Forum](https://forum.katalon.com/categories/katalon-analytics).
* Request a new feature on [GitHub](CONTRIBUTING.md).
* Vote for [Popular Feature Requests](https://github.com/katalon-analytics/katalon-analytics/issues?q=is%3Aopen+is%3Aissue+label%3Afeature-request+sort%3Areactions-%2B1-desc).
* File a bug in [GitHub Issues](https://github.com/katalon-analytics/katalon-analytics/issues).

### Katalon Studio
[Katalon Studio](https://www.katalon.com) is a free and complete automation testing solution for Web, Mobile, and API testing with modern methodologies (Data-Driven Testing, TDD/BDD, Page Object Model, etc.) as well as advanced integration (JIRA, qTest, Slack, CI, Katalon TestOps, etc.). Learn more about [Katalon Studio features](https://www.katalon.com/features/).
