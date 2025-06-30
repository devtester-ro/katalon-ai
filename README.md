# Katalon AI Framework

## Overview

**Katalon AI Framework** is an advanced automation framework that leverages Katalon Studio for web, API, and mobile application testing. This project provides a robust, scalable, and maintainable solution for automated testing across multiple platforms, following OOP and SOLID principles.

## Features

- **Web UI Automation**: Comprehensive framework for web application testing.
- **API Testing**: Structured approach for API test automation.
- **Mobile Automation**: Support for Android and iOS app testing.
- **OOP & SOLID Design**: Framework built on object-oriented programming and SOLID principles.
- **Page Object Model**: Implementation of the Page Object Model design pattern.
- **Service Object Pattern**: Implementation of the Service Object pattern for API testing.
- **Screen Object Pattern**: Implementation of the Screen Object pattern for mobile testing.
- **Reporting**: Enhanced reporting capabilities with detailed logs and screenshots.
- **Configuration Management**: Environment-specific configuration management.
- **Test Data Generation**: Utilities for generating test data.
- **Cross-Platform**: Unified approach for web, API, and mobile test cases.
- **Continuous Integration Ready**: Easily integrate with CI/CD pipelines for automated test execution.

## Project Structure

```
katalon-ai/
├── Keywords/                      # Custom keywords and framework classes
│   ├── base/                      # Base classes for the framework
│   │   ├── BaseTest.groovy        # Base class for all tests
│   │   ├── BasePage.groovy        # Base class for page objects
│   │   ├── BaseApiService.groovy  # Base class for API services
│   │   └── BaseMobileScreen.groovy # Base class for mobile screens
│   ├── util/                      # Utility classes
│   │   ├── ConfigurationManager.groovy # Configuration management
│   │   ├── DataGenerator.groovy   # Test data generation
│   │   └── ReportingUtil.groovy   # Enhanced reporting
│   ├── web/                       # Web-specific classes
│   │   ├── WebTest.groovy         # Base class for web tests
│   │   └── pages/                 # Page objects
│   ├── api/                       # API-specific classes
│   │   ├── ApiTest.groovy         # Base class for API tests
│   │   └── services/              # API service objects
│   └── mobile/                    # Mobile-specific classes
│       ├── MobileTest.groovy      # Base class for mobile tests
│       └── screens/               # Mobile screen objects
├── Object Repository/             # Test objects
│   ├── Page_Home/                 # Web UI objects for home page
│   └── Mobile/                    # Mobile objects
│       └── LoginScreen/           # Login screen objects
├── Test Cases/                    # Test cases
│   ├── web/                       # Web test cases
│   ├── api/                       # API test cases
│   └── mobile/                    # Mobile test cases
├── Test Suites/                   # Test suites
├── Profiles/                      # Execution profiles
│   └── default.glbl               # Default profile with global variables
├── Test Listeners/                # Test listeners
├── Include/                       # External files and resources
└── Scripts/                       # Custom scripts
```

## Getting Started

1. **Prerequisites**:
   - Katalon Studio (latest version recommended)
   - Java JDK 8 or higher
   - Git
   - Appium Server (for mobile testing)

2. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/katalon-ai.git
   ```

3. **Open the project in Katalon Studio**:
   - Launch Katalon Studio
   - Click "File" > "Open Project"
   - Select the cloned repository

4. **Configure the execution profile**:
   - Open the default profile in Profiles/default.glbl
   - Update the global variables as needed for your environment

5. **Run the tests**:
   - Individual test: Open a test case and click "Run"
   - Test suite: Open the AllTests test suite and click "Run"

## Framework Components

### Base Classes

- **BaseTest**: Foundation for all test cases with common test lifecycle management.
- **BasePage**: Foundation for all page objects with common web page interactions.
- **BaseApiService**: Foundation for all API service objects with common API interactions.
- **BaseMobileScreen**: Foundation for all mobile screen objects with common mobile interactions.

### Utility Classes

- **ConfigurationManager**: Manages configuration settings across different environments.
- **DataGenerator**: Provides methods for generating test data.
- **ReportingUtil**: Enhances test reporting with detailed logs and screenshots.

### Test Types

- **Web Testing**: Page Object Model implementation for web UI testing.
- **API Testing**: Service Object pattern implementation for API testing.
- **Mobile Testing**: Screen Object pattern implementation for mobile testing.

## Sample Tests

The framework includes sample tests for each type:

- **Web**: HomePageTest demonstrates web UI testing.
- **API**: UserApiTest demonstrates API testing.
- **Mobile**: LoginTest demonstrates mobile testing.

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
