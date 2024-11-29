Feature: login with user credentials

  @Olivera
  Scenario: Successful user login
    When user logins with valid credentials as "john.lee" and "SynTAX_78N"
    Then user navigates to the dashboard page
