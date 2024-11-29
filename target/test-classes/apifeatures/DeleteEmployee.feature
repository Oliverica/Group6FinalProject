Feature: Delete the employee

  Background:
    Given request to generate token with valid email "admin_access@google.com" and valid password "Password123" is prepared
    And POST request to generate token is called

  @5 @artem @delete1 @api
  Scenario: Create temporary employee
    Given a request is prepared for creating the employee using data "David", "Johns", "Mark", "M", "1973-02-11", "temporary","QA lead"
    When a POST call is made to create the employee
    Then the employee id "Employee.employee_id" is stored and values are validated

  @5 @artem @delete1 @api
  Scenario: Delete the created employee with valid ID
    Given a request is prepared to delete the created the employee by a stored Employee ID
    When a GET call is made to get the employee
    And user sees the details of the employee
    When a DEL call is made to delete the employee
    Then the message is "Employee deleted"

  @5 @artem @delete2 @api
  Scenario: Delete the employee with invalid or deleted ID
    Given a request is prepared to delete the employee with employee ID "115601Y" that does not exist
    When a DEL call is made to delete the employee
    Then user gets a message "Employee does not exist or you have provided invalid employee_id"

  @5 @artem @delete3
  Scenario: Delete the employee without providing ID
    Given a request is prepared to delete the created employee without ID
    When a DEL call is made to delete the employee
    Then user gets en error code 400
    And user gets an error "Please provide employee_id"
