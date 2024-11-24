Feature: Creating a new employee related scenarios

  Background:
    When user enters username and password
    And clicks on login button
    And user clicks on PIM option
    And user clicks on Add Employee button

  @addEmployee @addByFullName @verifyEmployeeInDB
  Scenario: Creating an employee without providing an employee ID
    Given user enters an employee full name
    When user clicks on Save button
    And user is navigated to the new employee profile page
    Then query "select employee_id, emp_firstname, emp_middle_name, emp_lastname from hs_hr_employees where employee_id = " is executed to fetch the employee details from the database

  @addEmployee @addByID @verifyEmployeeInDB
  Scenario: Creating an employee by providing an employee ID
    Given user enters an employee full name and a unique employee ID
    When user clicks on Save button
    And user is navigated to the new employee profile page
    Then query "select employee_id, emp_firstname, emp_middle_name, emp_lastname from hs_hr_employees where employee_id = " is executed to fetch the employee details from the database

  @addEmployee @errorMessageValidation @errorMessage
  Scenario: Validating an error message when user attempts to submit or provide incomplete information
    And user enters middlename
    And user clicks on Save button
    Then clear error message or prompts is displayed for firstname and lastname fields

  @addEmployee @existingIdError @failedToSave @errorMessageValidation
  Scenario: Validating an error message when user attempts to add an employee with an existing ID
    And user enters an employee full name and existing ID "2023962"
    And user clicks on Save button
    Then clear error message is displayed




