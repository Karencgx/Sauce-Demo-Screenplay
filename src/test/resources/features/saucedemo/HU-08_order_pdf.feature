@HU-08
Feature: HU-08 Download the order PDF
  As a customer
  I want to download the PDF of my order
  So that I keep a proof of my purchase

  Background:
    Given the customer opens the SauceDemo store
    And the customer is logged in as "standard_user"

  Scenario: The order PDF is generated after finishing the purchase
    Given the customer adds the products "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    And the customer finishes the purchase
    And the customer generates the order PDF
    Then the order PDF is downloaded
