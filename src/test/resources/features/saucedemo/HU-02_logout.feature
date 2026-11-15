@HU-02
Feature: HU-02 Log out of the store
  As a customer
  I want to log out of the store
  So that my account stays protected

  Background:
    Given the customer opens the SauceDemo store

  Scenario: Successful logout from the menu
    Given the customer is logged in as "standard_user"
    When the customer logs out from the menu
    Then the customer is back on the login screen

  Scenario: The catalog cannot be opened without a session
    When the customer tries to open the catalog page directly without logging in
    Then the system shows the error message "Epic sadface: You can only access '/inventory.html' when you are logged in."
