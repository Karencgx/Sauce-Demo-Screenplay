@HU-05
Feature: HU-05 Manage the shopping cart
  As a customer
  I want to add and remove products in my cart
  So that I choose exactly what I want to buy

  Background:
    Given the customer opens the SauceDemo store
    And the customer is logged in as "standard_user"

  Scenario: A new cart has no products
    When the customer opens the cart
    Then the cart has no products

  Scenario Outline: Adding a product updates the cart icon
    When the customer adds the product "<product>" to the cart
    Then the cart icon shows the number 1
    And the product "<product>" is in the cart

    Examples:
      | product                 |
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Onesie       |

  Scenario: The cart icon counts every added product
    When the customer adds the products "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    Then the cart icon shows the number 2

  Scenario: A product can be removed from the cart
    When the customer adds the products "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    And the customer removes the product "Sauce Labs Bike Light" from the cart
    Then the cart shows 1 product
    And the product "Sauce Labs Backpack" is in the cart

  Scenario: The cart can be emptied completely
    When the customer adds the products "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    And the customer removes the product "Sauce Labs Backpack" from the cart
    And the customer removes the product "Sauce Labs Bike Light" from the cart
    Then the cart has no products

  Scenario: Products are kept when the customer keeps shopping
    When the customer adds the product "Sauce Labs Backpack" to the cart
    And the customer opens the cart
    And the customer goes back to the catalog to keep shopping
    And the customer adds the product "Sauce Labs Onesie" to the cart
    Then the cart shows 2 products
    And the product "Sauce Labs Backpack" is in the cart
    And the product "Sauce Labs Onesie" is in the cart
