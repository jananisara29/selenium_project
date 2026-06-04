Feature: Login functionality

  Scenario: Valid login
    Given user is on saucedemo login page
    When user enters username "standard_user" and password "secret_sauce"
    Then user should be redirected to inventory page

  Scenario: Invalid login
    Given user is on saucedemo login page
    When user enters username "wrong_user" and password "wrong_pass"
    Then user should see error message

  Scenario: Empty login
    Given user is on saucedemo login page
    When user clicks login without entering credentials
    Then user should see error message