Feature: Cart functionality

  Scenario: Add product to cart
    Given user is logged in
    When user adds first product to cart
    Then user should be redirected to cart page

  Scenario: Verify cart is not empty
    Given user is logged in
    When user adds first product to cart
    Then cart should contain items