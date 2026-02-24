# Build & Run Instructions

## Build
- ./gradlew assembleAndroidTest

## Run Tests
- ./gradlew connectedAndroidTest

## Troubleshooting
- Ensure emulator/device is running
- Disable animations on device
- Verify Espresso Idling Resources are registered

## Usage Guidelines
- Follow 1:1 mapping between feature files and step definitions
- Keep Page Objects free of assertions
- Centralize waits using WaitUtils