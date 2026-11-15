@HU-09
Feature: HU-09 Use the store with every user profile
  As a customer of any profile
  I want to use the store without errors
  So that I can shop regardless of my user type

  Background:
    Given the customer opens the SauceDemo store

  Scenario Outline: Every valid user can log in
    When the customer logs in as "<username>"
    Then the customer sees the product catalog

    Examples:
      | username                |
      | standard_user           |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |

  Scenario Outline: Users without defects in the flow can complete a purchase
    Given the customer is logged in as "<username>"
    And the customer adds the product "Sauce Labs Backpack" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    And the customer finishes the purchase
    Then the system shows the confirmation message "Thank you for your order!"

    Examples:
      | username                |
      | standard_user           |
      | performance_glitch_user |
      | visual_user             |

  Scenario Outline: The catalog prices match the published ones
    Given the customer is logged in as "<username>"
    Then the catalog price of "Sauce Labs Backpack" is "$29.99"
    And the catalog price of "Sauce Labs Bike Light" is "$9.99"
    And the catalog price of "Sauce Labs Bolt T-Shirt" is "$15.99"
    And the catalog price of "Sauce Labs Fleece Jacket" is "$49.99"

    Examples:
      | username                |
      | standard_user           |
      | performance_glitch_user |

  Scenario Outline: Products that fail for other profiles can be added by the standard user
    Given the customer is logged in as "standard_user"
    When the customer adds the product "<product>" to the cart
    Then the cart icon shows the number 1

    Examples:
      | product                           |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Fleece Jacket          |
      | Test.allTheThings() T-Shirt (Red) |
