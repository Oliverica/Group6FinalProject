Feature: Add Dependents to Employee Profile

  Background:
    Given The user is authenticated

  Scenario Outline: Add a dependent with a valid data
    When the user sends a request  to add a dependent with "<name>", "<relationship>" and "<dateOfBirth>"
    Then the response should return "<expectedMessage>"
    Examples:
      |name          |relationship|dateOfBirth|expectedMessage             |
      |Michael Smith |Spouse      |1985-01-11 |Dependant added successfully|
      |Michael Lee   |Child       |2000-01-11 |Dependant added successfully|
      |Michael Jordan|Parent      |1960-01-11 |Dependant added successfully|

  Scenario Outline: Add a dependent with invalid date of birth format
    When the user sends a request to add a dependent with "<name>", "<relationship>" and "<dateOfBirth>"
    Then the response should return "<expectedMessage>"
    Examples:
      |name          |relationship|dateOfBirth|expectedMessage           |
      |Michael Smith |Spouse      |1985-11-01 |Error: Invalid date format|
      |Michael Lee   |Child       |2000/01/11 |Error: Invalid date format|

  Scenario Outline: Add a dependent with with missing or invalid fields
    When the user sends a request to add a dependent with "<name>", "<relationship>" and "<dateOfBirth>"
    Then the response should return "<expectedMessage>"
    Examples:
      |name          |relationship|dateOfBirth|expectedMessage                    |
      |              |            |1985-01-11 |Error: Name cannot be empty        |
      |Michael Lee   |Child       |2000-01-11 |Error: Relationship cannot be empty|
      |Michael Jordan|Parent      |           |Error: Date of birth is required   |

  Scenario: Add multiple dependents for an employee
    When the user sends a request to add a dependent with a following data:
      |name          |relationship|dateOfBirth|
      |Michael Smith |Spouse      |1985-01-11 |
      |Michael Lee   |Child       |2000-01-11 |
    Then the response should confirm all dependents were added successfully

  Scenario: Display dependents in the HRMS system
    When the user views  the dependent list for an employee
    Then the dependent list should display all added dependents