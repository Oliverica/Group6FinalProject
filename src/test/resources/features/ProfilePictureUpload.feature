Feature: Profile Photo Upload

  Background:
    When user enters username and password
    And clicks on login button
    And user clicks on PIM option
    And user clicks on employee list option

  @profilePhotoUpload @validProfilePhoto
  Scenario: Uploading a photo on an employee's profile
    When user navigates to the employee profile by providing employee id "42843534"
    When user selects a file with a .jpg, .png, or .gif extension under 1MB and dimensions are 200px x 200px
    Then profile picture is successfully uploaded

  @profilePhotoUpload @unsupportedFileType
    Scenario: