Feature:

  Background:
    When user enters valid credentials
    And clicks login button
    And user clicks PIM option
    And user clicks employee list option
    And user navigates to the employee profile by providing employee ids "42843534"
    When user clicks on employee id and click membership button
    And user clicks on Top Add button

  @CheckAndValidateFields
  Scenario: Check and validate required fields
    Then user is navigated to MembershipAdd and fields are checked

    @MandatoryFieldsError
    Scenario:MandatoryFields
      When users select all the required details "2223132", "2024-11-10""
      And user press Save Bt
      Then Error message is coming up "Required"

  @SuccessMessage
  Scenario: Success Message
    When users select all the required details "2223132", "2024-11-10""
    And user press Save Bt
    Then Success message is coming up "Successfully Saved" displayed and enabled


