# Katalon Android - Mobile Testing Framework

> **Note:** This project is part of the [Katalon AI](../README.md) unified testing framework.

## Overview

The Katalon Android project provides a comprehensive framework for mobile testing on Android devices. It demonstrates UI functional automation tests on Android applications using Katalon Studio with minimal programming requirements.

## Features

- **Mobile UI Testing**: Automated UI tests for Android applications
- **Mobile Browser Testing**: Tests for mobile web browsers
- **Object Repository**: Reusable UI element definitions for Android apps
- **Test Suites**: Organized test suites for different testing scenarios
- **Sample Application**: Includes tests for the API Demos sample application

## Project Structure

```
katalon-android/
├── Object Repository/           # UI element definitions
│   ├── Application/             # Native app UI elements
│   └── Web Browser/             # Mobile browser UI elements
├── Scripts/                     # Test implementation scripts
├── Test Cases/                  # Test case definitions
├── Test Suites/                 # Test suite configurations
├── androidapp/                  # Sample Android applications
│   └── APIDemos.apk             # API Demos sample app
├── build.gradle                 # Gradle build configuration
└── README.md                    # This file
```

## Getting Started

### Prerequisites

- [Katalon Studio](https://www.katalon.com/) - [Installation and Setup](https://docs.katalon.com/x/HwAM)
- [Android Studio](https://developer.android.com/studio/)
- Android device or emulator
- Application Under Test (AUT)
  - [APIDemos.apk](https://github.com/katalon-studio-samples/android-mobile-tests/tree/master/androidapp/APIDemos.apk)

### Setting Up

1. Clone this repository or the parent Katalon AI project
2. Open the project in Katalon Studio
3. Connect an Android device or start an emulator
4. Install the sample application on the device/emulator

### Setting Up Android Environment

#### 1. Install Android Studio
- Download and install Android Studio from [developer.android.com/studio](https://developer.android.com/studio)
- Follow the installation wizard to complete the setup
- Make sure to install the Android SDK during the setup process

#### 2. Set Up Android Virtual Device (AVD)
- Open Android Studio
- Go to Tools -> Device Manager or visit [developer.android.com/studio/run/managing-avds](https://developer.android.com/studio/run/managing-avds)
- Click "Create Virtual Device" and follow the wizard to create an emulator
- Select a device definition (e.g., Pixel 4)
- Select a system image (recommended: x86 images for better performance)
- Configure the AVD options and finish the creation

#### 3. Set Up Appium
- Make sure you have Node.js and NPM installed
- Install Appium globally:
  ```bash
  npm install -g appium
  ```
- Install the required driver for Android:
  ```bash
  appium driver install uiautomator2
  ```

#### 4. Running Tests with Android Emulator
- Start your Android emulator from Android Studio's Device Manager
- In Katalon Studio, select your test case
- Choose "Android" as the device type
- Select your emulator from the device list
- Run the test

### Executing a Test Case

![Execute a simple test case](https://github.com/katalon-studio-samples/android-mobile-tests/blob/master/Tutorials/Figures/_Execute%20a%20test%20case.png?raw=true)

1. Expand the Test Cases structure, where all the test cases are stored.
2. Select the test case you want to execute
3. Choose the mobile device for executing the test case

#### Via Command Line

```bash
../gradlew katalonRunTest -Dtestcase="Verify Correct Alarm Message"
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

## See Also

- Update configurations for integration: [Jira](https://docs.katalon.com/x/7oEw), [Katalon Analytics](https://docs.katalon.com/x/KRhO)
- Katalon Documentation: http://docs.katalon.com/, especially some [Tips and Tricks](https://docs.katalon.com/x/PgXR) to run a successful automation test.
- Katalon Forum: https://forum.katalon.com/
- Katalon Business Support: https://www.katalon.com/support-service-options/
