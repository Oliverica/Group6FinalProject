Feature: Create login details for an employee
  As an HRMS admin,
  I want to create login credentials for employees
  So that they can access the HRMS system with unique credentials

  Background:
    When user logs in with valid credentials
    And user navigates to the dashboard page
    Then user clicks on PIM option
    And user clicks on add employee option


   @enable
  Scenario: Enable login fields with the checkbox checked
    When user selects the checkbox "Create login details"
    Then username,password,confirm password, and status field should be enabled

@checkboxChecked
  Scenario: Create login details with the checkbox checked
    When user fills mandatory fields "John","Lee", and "12345" for First Name,Last Name, and Employee ID
    And user selects the checkbox "Create login details" in the Add Employee page
    Then username, password fields, and status field are enabled
    When user enters "john.lee" as username
    And user enters "PASSWORD!!pass456" as password
    And user confirms the password "PASSWORD!!pass456"
    And user selects "Enabled" as the status from the dropdown
    And user clicks "Save"
    Then user should be navigated to the "Employee Profile" page

@passwordMismatch
  Scenario: Error displayed for password mismatch
    When user fills mandatory fields "Jane" and "Brown" for First Name and Last Name
    And user selects the "Create login details"
    When user sets "jane.brown" as username
    And user enters "PASSWORD!!pass789" as the password
    And user confirms password as "wrongPASS123!"
    And user clicks the "Save" button
    Then error message "Passwords do not match" should be displayed