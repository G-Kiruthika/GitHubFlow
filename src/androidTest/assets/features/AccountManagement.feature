Feature: Account Management

  Scenario: View account details
    Given user is on landing page
    When user logs in with valid credentials
    Then account details should be displayed