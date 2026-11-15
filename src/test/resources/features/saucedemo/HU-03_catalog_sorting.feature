@HU-03
Feature: HU-03 Sort the product catalog
  As a customer
  I want to sort the catalog
  So that I can find products quickly

  Background:
    Given the customer opens the SauceDemo store
    And the customer is logged in as "standard_user"

  Scenario: The catalog shows all the available products
    Then the catalog shows 6 products

  Scenario Outline: The catalog can be sorted by different criteria
    When the customer sorts the catalog by "<criterion>"
    Then the first product in the catalog is "<first_product>"

    Examples:
      | criterion           | first_product                     |
      | Name (A to Z)       | Sauce Labs Backpack               |
      | Name (Z to A)       | Test.allTheThings() T-Shirt (Red) |
      | Price (low to high) | Sauce Labs Onesie                 |
      | Price (high to low) | Sauce Labs Fleece Jacket          |
