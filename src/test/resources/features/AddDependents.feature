@addDependents
Feature: Add Dependents to Employee Profile

  Background:
    When user logs in with valid credentials
    Then user is navigated to dashboard page
    When the user navigates to the Employee List
    And the user searches for the employee "Olala" with ID "115720A"
    Then user clicks search button
    And the employee details should be displayed
    Then the user selects the employee "115720A"
    And the user is on the Personal Details Page
    Then the user clicks on Dependent tab
    And the user clicks on Add button to add dependent

  @getTheUser @group6
  Scenario Outline: Add a dependent with a valid data
    When the user enters dependent name as "<name>"
    And the user selects relationship as "<relationship>"
    Then the user selects date of birth as "<dateOfBirth>"
    And the user clicks on "SAVE" button
    Then the dependent "<name>" should be added successfully
    Examples:
      |name          |relationship|dateOfBirth|
      |Jane Miller   |Child       |1960-09-11 |

  @missingOrInvalidFields @group6
  Scenario Outline: Add a dependent with with missing or invalid fields
    When the user sends a request to add a dependent with a following data: a dependent with "<name>",and "<relationship>"
    Then the response should return "<expectedMessage>"
    Examples:
      |name          |relationship |expectedMessage |
      |Mike          |             |Required        |
      |              |Parent       |Required        |

  @addMultiple @group6
  Scenario Outline: Add multiple dependents for an employee
    When the user sends a request to add a dependent with a following data:
      |name          |relationship  |
      |<name>        |<relationship>|
    Then the response should confirm all dependents were added successfully:
      |name          |relationship  |
      |<name>        |<relationship>|
    Examples:
      |name          |relationship|
      |John Miller   |Child       |
      |Jack Miller   |Child       |

  @edit
  Scenario: Display dependents in the HRMS system and make sure they are editable
    When the user views  the dependent list for an employee
    Then the dependent list should display all added dependents
    When the user selects a dependent to edit with name "Jack Miller"
    And the user updates the dependent's information as "Jacky"
    Then the dependent information should be updated successfully

  @delete
  Scenario: Verify successful deletion
    When the user clicks on Dependents tab
    And the user selects a dependent to delete "Jane Miller"
    And the user clicks on the Delete button
    Then the dependent "Jane Miller" should be removed from the list