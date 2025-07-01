# Katalon AI - Unified Testing Framework

## Overview

Katalon AI is a comprehensive testing framework that unifies three specialized testing projects under a single umbrella:

1. **Katalon API** - REST API testing using REST Assured with the Swagger Petstore API
2. **Katalon Web** - Web UI testing with BDD approach for a Calculator application
3. **Katalon Android** - Mobile testing for Android applications

This unified project demonstrates how to organize and manage multiple testing types (API, Web, Mobile) in a single repository with a consistent build system.

## Project Structure

```
katalon-ai/
├── katalon-api/              # API testing project
├── katalon-web/              # Web UI testing project
├── katalon-android/          # Android mobile testing project
├── gradle/                   # Gradle wrapper directory
├── build.gradle              # Root build file with common configuration
├── settings.gradle           # Project settings file
├── gradlew                   # Gradle wrapper script for Unix
├── gradlew.bat               # Gradle wrapper script for Windows
└── README.md                 # This file
```

## Subprojects

### Katalon API

A project demonstrating REST API testing using REST Assured library with the Swagger Petstore API. It showcases:

- Pet management (create, update, delete, find by status/tags)
- Store operations (inventory, orders)
- User management (create, update, delete, login/logout)

The project demonstrates two different approaches to API testing:
1. Using REST Assured library directly
2. Using Katalon's built-in WS Keywords

[More details in the katalon-api README](./katalon-api/README.md)

### Katalon Web

A project demonstrating web UI testing with a BDD (Behavior-Driven Development) approach for a Calculator application. It includes:

- Feature files describing calculator operations
- Step definitions implementing the test logic
- Page objects representing the calculator UI
- Test suites for verifying calculator operations

[More details in the katalon-web README](./katalon-web/README.md)

### Katalon Android

A project demonstrating mobile testing for Android applications. It includes:

- UI functional automation tests for Android applications
- Tests for the API Demos sample application
- Mobile browser testing capabilities
- Object Repository for Android UI elements

[More details in the katalon-android README](./katalon-android/README.md)

## Getting Started

### Prerequisites

- [Katalon Studio](https://www.katalon.com/) (version 10.2.0 or later)
- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (version 8 or later)
- [Android Studio](https://developer.android.com/studio/) (for Android tests)
- [Git](https://git-scm.com/) (for version control)

### Setup

1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/katalon-ai.git
   cd katalon-ai
   ```

2. Initialize the Gradle wrapper (if gradle-wrapper.jar is missing):
   ```bash
   gradle wrapper
   ```

3. Build the project:
   ```bash
   ./gradlew build
   ```

4. Open the projects in Katalon Studio:
   - For API testing: Open katalon-api/katalon-api.prj
   - For Web testing: Open katalon-web/katalon-web.prj
   - For Android testing: Open katalon-android/katalon-android.prj

## Running Tests

### Via Katalon Studio UI

1. Open the respective project in Katalon Studio
2. Navigate to Test Cases
3. Right-click on a test case and select "Run"

### Via Command Line

For API tests:
```bash
./gradlew :katalon-api:katalonRunTest -Dtestcase="REST Assured/Pet/Verify Get Pet"
```

For Web tests:
```bash
./gradlew :katalon-web:katalonRunTest -Dtestcase="operations/Verify Addition"
```

For Android tests:
```bash
./gradlew :katalon-android:katalonRunTest -Dtestcase="Verify Correct Alarm Message"
```

## Technology Stack

- **Katalon Studio**: Test automation platform
- **Gradle**: Build automation
- **Groovy**: Scripting language for test implementation
- **REST Assured**: Java library for REST API testing
- **Selenium**: Web browser automation
- **Appium**: Mobile app automation

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Run tests to ensure they pass
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- [Katalon Studio](https://www.katalon.com/) for the test automation platform
- [Swagger Petstore](https://petstore.swagger.io/) for the API testing endpoint
- The Android Open Source Project for the API Demos sample application