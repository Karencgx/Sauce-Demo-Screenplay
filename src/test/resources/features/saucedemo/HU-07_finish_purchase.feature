@HU-07
Feature: HU-07 Finish the purchase
  As a customer
  I want to finish my purchase
  So that I receive the confirmation of my order

  Background:
    Given the customer opens the SauceDemo store
    And the customer is logged in as "standard_user"

  Scenario Outline: The purchase is confirmed for different products
    Given the customer adds the product "<product>" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    And the customer finishes the purchase
    Then the system shows the confirmation message "Thank you for your order!"

    Examples:
      | product               |
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
      | Sauce Labs Onesie     |

  Scenario: The purchase of several products is confirmed
    Given the customer adds the products "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    And the customer finishes the purchase
    Then the system shows the confirmation message "Thank you for your order!"

  Scenario: The cart is empty after finishing the purchase
    Given the customer adds the product "Sauce Labs Backpack" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    And the customer finishes the purchase
    And the customer returns to the catalog from the confirmation
    Then the cart icon shows no number

  Scenario: The customer can log out after buying
    Given the customer adds the product "Sauce Labs Backpack" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    And the customer finishes the purchase
    And the customer returns to the catalog from the confirmation
    And the customer logs out from the menu
    Then the customer is back on the login screen
