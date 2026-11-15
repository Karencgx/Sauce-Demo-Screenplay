@HU-06
Feature: HU-06 Enter the shipping data
  As a customer
  I want to enter my shipping data at checkout
  So that I can review my order before paying

  Background:
    Given the customer opens the SauceDemo store
    And the customer is logged in as "standard_user"

  Scenario Outline: Checkout is rejected when the shipping data is incomplete
    Given the customer adds the product "Sauce Labs Backpack" to the cart
    When the customer starts the checkout with first name "<first_name>", last name "<last_name>" and postal code "<postal_code>"
    Then the system shows the error message "<message>"

    Examples:
      | first_name | last_name | postal_code | message                        |
      |            | Perez     | 050001      | Error: First Name is required  |
      | Juan       |           | 050001      | Error: Last Name is required   |
      | Juan       | Perez     |             | Error: Postal Code is required |

  Scenario Outline: The order summary shows the correct totals
    Given the customer adds the product "<product>" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    Then the order summary shows the subtotal "<subtotal>"
    And the order summary shows the tax "<tax>"
    And the order summary shows the total "<total>"

    Examples:
      | product               | subtotal | tax   | total  |
      | Sauce Labs Backpack   | $29.99   | $2.40 | $32.39 |
      | Sauce Labs Bike Light | $9.99    | $0.80 | $10.79 |
      | Sauce Labs Onesie     | $7.99    | $0.64 | $8.63  |

  Scenario: The order summary adds up several products
    Given the customer adds the products "Sauce Labs Backpack" and "Sauce Labs Bike Light" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    Then the order summary shows the subtotal "$39.98"
    And the order summary shows the tax "$3.20"
    And the order summary shows the total "$43.18"

  Scenario: Cancelling the checkout keeps the products in the cart
    Given the customer adds the product "Sauce Labs Backpack" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    And the customer cancels the purchase
    Then the cart shows 1 product
    And the product "Sauce Labs Backpack" is in the cart
