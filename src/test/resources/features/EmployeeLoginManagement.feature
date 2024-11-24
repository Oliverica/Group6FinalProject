Feature: Managing Employee Login Credentials

  Background:
    Given user enters username "<username>" and password "<password>"
    And clicks on login button
    And user clicks on PIM option
    And user clicks on Add Employee button

    @addEmployeeLogin
    Scenario: Creating login credentials for a new employee
      Given user enters an employee full name "<employeeName>"
      And user selects "<status>" as the status
      And user selects login credentials
      And user enters username "<newUsername>"
      And user enters password "<newPassword>"
      And user confirms password "<newPassword>"
      When user clicks on Save button
      Then user is navigated to the employee profile page
      Then  user should see the username "<newUsername>" in the database query results

      @validateInvalidPassword
      Scenario: Validating error message for invalid password format
        Given user enters an employee full name "<employeeName>"
        And user selects "<status>" as the status
        And user selects login credentials
        And user enters username "<newUsername>"
        And user enters password "<invalidPassword>"
        And user confirms password "<invalidPassword>"
        When user clicks on Save button
        Then error message should be displayed

        @deleteEmployeeLogin
        Scenario: deleting an employee login credential
          Given user searches for an employee with username "<usernameToDelete>"
          And user clicks on Delete button
          When user confirms the deletion
          Then user is notified that deletion has been completed susccessfully
          And query "select * from hs_hr_users where user_name="<usernameToDelete>" confirms the username is deleted