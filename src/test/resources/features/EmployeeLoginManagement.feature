Feature: Managing Employee Login Credentials

  Background:
    Given user enters username and password
    And clicks on login button
    And user clicks on PIM option
    And user clicks on Add Employee button

    @addEmployeeLogin
    Scenario: Creating login credentials for a new employee
      Given user enters an employee full name
      And user selects "Enabled" as the status
      And user selects login credentials
      And user enters username "Jackie Jones"
      And user enters password "Pasword..122"
      And user confirms password "Pasword..122"
      When user clicks on Save button
      Then user is navigated to the employee profile page
      Then  user should see the username "Jackie Jones" in the database query results

      @validateInvalidPassword
      Scenario: Validating error message for invalid password format
        Given user enters an employee full name
        And user selects "Enabled" as the status
        And user selects login credentials
        And user enters username "JackJohn456"
        And user enters password "Pass"
        And user confirms password "Pass"
        When user clicks on Save button
        Then error message should be displayed

        @deleteEmployeeLogin
        Scenario: deleting an employee login credential
          Given user searches for an employee with username "Bob908070"
          And user clicks on Delete button
          When user confirms the deletion
          Then user is notified that deletion has been completed susccessfully
          And query "select * from hs_hr_users where user_name='Bob908070' confirms the username is deleted