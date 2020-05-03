Feature: Searching Port

  Scenario: Searching the port of destination
    Given user is on landing page
    When user goes to "EXPLORE" tab
    And user selects "PORTS" submenu
    And user searches the port of "Miami"
    Then port is in the middle of the map
    Then label of the port departure is as "PORT OF DEPARTURE"

