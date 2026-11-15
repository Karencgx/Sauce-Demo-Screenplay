@HU-04
Feature: HU-04 View the detail of a product
  As a customer
  I want to see the detail of a product
  So that I can decide whether to buy it

  Background:
    Given the customer opens the SauceDemo store
    And the customer is logged in as "standard_user"

  Scenario Outline: The detail page shows the name and the price of each product
    When the customer opens the details of the product "<product>"
    Then the details show the name "<product>"
    And the details show the price "<price>"

    Examples:
      | product                           | price  |
      | Sauce Labs Backpack               | $29.99 |
      | Sauce Labs Bike Light             | $9.99  |
      | Sauce Labs Bolt T-Shirt           | $15.99 |
      | Sauce Labs Fleece Jacket          | $49.99 |
      | Sauce Labs Onesie                 | $7.99  |
      | Test.allTheThings() T-Shirt (Red) | $15.99 |

  Scenario Outline: A product can be added to the cart from its detail page
    When the customer opens the details of the product "<product>"
    And the customer adds the product to the cart from the details
    Then the cart icon shows the number 1

    Examples:
      | product             |
      | Sauce Labs Backpack |
      | Sauce Labs Onesie   |
