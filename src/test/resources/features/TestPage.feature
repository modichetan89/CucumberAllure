@TestPageTag
Feature: Navigate to cart feature

  @Smoke
  Scenario: verify home page title and navigation to cart fail
    Given user is on home page "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in"
    When user click on cart icon
    Then user navigated to cart "Amazon.in Shopping Cart HHHHHHHHHH"

  @Smoke
  Scenario: verify home page title and navigation to cart
    Given user is on home page "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in"
    When user click on cart icon
    Then user navigated to cart "Amazon.in Shopping Cart"
