Feature: Add the membership details as an employee

  Background:
    Given employee logs in with valid username "JackJohn88" and password "Isabella_Carter@123!"
    When employee clicks on My Info button
    And employee clicks on Memberships button

  @addMembership @validData @validateMandatoryMembershipField
  Scenario: Add the membership as an employee
    Given employee clicks on Add membership button
    When employee selects paid by "Company"
    And employee enters subscription amount "500"
    And employee selects "Euro" from the currency dropdown menu
    And employee selects "2024-11-01" as commence date
    And employee selects "2024-11-30" as renewal date
    Then employee clicks on Save membership button
    And mandatory membership field error "Required" is displayed
    When employee selects "Membership1" from dropdown menu
    And employee clicks on Save membership button
    Then message "Successfully Saved" is displayed

  @editMembership
  Scenario: Edit the membership
    When employee clicks on the membership "Membership1"
    And changes commence date to "2024-12-01"
    And changes renewal date to "2025-01-01"
    And employee clicks on Save membership button
    Then message "Successfully Saved" is displayed

  @deleteMembership
  Scenario: Delete the membership as an employee
    When employee select "Membership1" to delete
    And employee clocks on Delete button
    Then message for deleted membership "Successfully Deleted" is displayed



