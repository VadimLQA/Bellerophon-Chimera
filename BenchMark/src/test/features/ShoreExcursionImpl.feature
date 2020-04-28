#Each feature file contains one feature
# Feature file uses Gherkin language - business language
Feature: Test Port navigation functionality

  # A feature may have given different specific scenarios
  Scenario: User should be able to find wanted port and see its in the middle of the screen
    Given Lands on the main page
    When User navigates to explore
    Then User is able to find the port
