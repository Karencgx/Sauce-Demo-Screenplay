@HU-01
Feature: HU-01 Log in to the store
  As a customer
  I want to log in to the store
  So that I can access the product catalog

  Background:
    Given the customer opens the SauceDemo store

  Scenario: Successful login with a valid user
    When the customer logs in as "standard_user"
    Then the customer sees the product catalog
    And the catalog shows 6 products

  Scenario: A locked out user cannot log in
    When the customer logs in as "locked_out_user"
    Then the system shows the error message "Epic sadface: Sorry, this user has been locked out."

  Scenario Outline: Login is rejected with invalid or incomplete credentials
    When the customer logs in with username "<username>" and password "<password>"
    Then the system shows the error message "<message>"

    Examples:
      | username      | password       | message                                                                   |
      | standard_user | wrong_password | Epic sadface: Username and password do not match any user in this service |
      | unknown_user  | wrong_password | Epic sadface: Username and password do not match any user in this service |
      |               | any_password   | Epic sadface: Username is required                                        |
      | standard_user |                | Epic sadface: Password is required                                        |
