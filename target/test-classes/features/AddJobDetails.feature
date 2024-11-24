Feature:Job Details
  Background:

  @JobDetails
  Scenario: Adding Membership Details as an Employee
    Given user is able to access HRMS application
    When user clicks PIM option and Employee List
    And user clicks any Employee id from the list
    And user clicks Job Button
    Then all the required fields are displayed and editable and validated
      | Job Title         |
      | Employment Status |
      | Job Category      |
      | Joined Date       |
      | Sub Unit          |
      | Location          |
      | Start Date        |
      | End Date          |
      | Contract Details  |

     @mandatoryfields
    Scenario:test Save Without Mandatory Fields
       Given user is able to access HRMS application
       When user clicks PIM option and Employee List
       And user clicks any Employee id from the list
       And user clicks Job Button
       And user clicks saveEdit button
       When the Admin fills in the job details for the employee:
         | JobTitle     | Employment Status | job Category | joined Date  | Sub Unit    | Location    |
         | IT Support   | Full-time        | Sales       | 2023-01-01  | Finance    | New York    |
       Then the system should validate that all mandatory fields are filled
       And the system should save the job details in the employee's profile
       And the changes should be reflected in the database










