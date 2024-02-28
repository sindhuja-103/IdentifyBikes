Feature: ZigWheels

  Scenario: 1 Upcoming Bikes
    Given user navigated to ZigWheels Website
    When user hover on NewBikes
    Then click on upcoming bikes
    Then click on manufacturer
    And select honda
    And click on readmore
    Then get the information about upcoming bikes and print the same in excel

  Scenario: 2 PopularCars in Chennai
    Given user will navigate to ZigWheels Website
    When user hover on used cars
    Then click on chennai
    Then get the information about popular models and print the same in excel

  Scenario: 3 Login with Google
    Given navigate to homepage of zigwheels
    When user clicks on login button
    Then click on google
    And provide invalid email-id
    Then click on next
    Then capture the error message
