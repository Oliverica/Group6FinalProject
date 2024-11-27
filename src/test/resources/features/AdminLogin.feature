Feature: Admin Login to the HRMS website
Given website address and valid credentials in config.properties

  @login @artem
Scenario: Successful admin login
When user enters username and password
And clicks on login button
Then user navigates to the dashboard page
