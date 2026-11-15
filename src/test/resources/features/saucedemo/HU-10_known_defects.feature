@HU-10 @known-defects
Feature: HU-10 Document the defects of the test users
  As a quality team
  I want to document the defects of the SauceDemo test users
  So that the suite proves which behaviors are wrong
  # These scenarios describe the CORRECT behavior. They are expected to FAIL:
  # every failure is a real defect that SauceDemo ships on purpose in these profiles.

  Background:
    Given the customer opens the SauceDemo store

  # Defect: visual_user sees prices that differ from the published ones
  Scenario: DEFECT - the visual_user catalog prices do not match the published ones
    Given the customer is logged in as "visual_user"
    Then the catalog price of "Sauce Labs Backpack" is "$29.99"
    And the catalog price of "Sauce Labs Bike Light" is "$9.99"
    And the catalog price of "Sauce Labs Bolt T-Shirt" is "$15.99"
    And the catalog price of "Sauce Labs Fleece Jacket" is "$49.99"

  # Defect: problem_user cannot add these three products (Backpack, Bike Light and Onesie work)
  Scenario: DEFECT - problem_user cannot add the Bolt T-Shirt to the cart
    Given the customer is logged in as "problem_user"
    When the customer adds the product "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart icon shows the number 1

  Scenario: DEFECT - problem_user cannot add the Fleece Jacket to the cart
    Given the customer is logged in as "problem_user"
    When the customer adds the product "Sauce Labs Fleece Jacket" to the cart
    Then the cart icon shows the number 1

  Scenario: DEFECT - problem_user cannot add the red T-Shirt to the cart
    Given the customer is logged in as "problem_user"
    When the customer adds the product "Test.allTheThings() T-Shirt (Red)" to the cart
    Then the cart icon shows the number 1

  # Defect: error_user can continue the checkout without a last name
  Scenario: DEFECT - error_user checkout does not require the last name
    Given the customer is logged in as "error_user"
    And the customer adds the product "Sauce Labs Backpack" to the cart
    When the customer starts the checkout with first name "Juan", last name "" and postal code "050001"
    Then the system shows the error message "Error: Last Name is required"

  # Defects: problem_user mixes up the form letters; error_user cannot finish the order
  Scenario Outline: DEFECT - users with a broken checkout cannot complete a purchase
    Given the customer is logged in as "<username>"
    And the customer adds the product "Sauce Labs Backpack" to the cart
    When the customer starts the checkout with first name "Juan", last name "Perez" and postal code "050001"
    Then the customer sees the order summary
    When the customer finishes the purchase
    Then the system shows the confirmation message "Thank you for your order!"

    Examples:
      | username     |
      | problem_user |
      | error_user   |
