Feature:

  @Jobdetails
  Scenario: Adding current job details for employees as an Admin.
    Given user is able to access HRMS application
    When user press Admin button and Job button
    Then Job Titles, Employment Status, and Job Category is displayed and enable


