Feature: Search for employees by their name or employee ID

Background:
  When user enters username and password
  And clicks on login button
  And user clicks on PIM option

  @artem @search
Scenario: Search for employee by full name
  When user enters "Boris Grebenschikov"
  And user clicks Search button
  Then user is able to see the employee

  @artem @search
  Scenario Outline: Search for employee by partial and ignore case name
    When user enters "<name>" regarding case in Employee Name field
    And user clicks Search button
    Then user is able to see some results
    Examples:
    |name|
    |Boris|
    |boris|
    |Bori |
    |boriS|
    |greben|
    |gReBeN|

    @artem @search
  Scenario: Search for employee by id
    When user enters valid employee ID "115601A" in the ID field
    And user clicks Search button
    Then user is able to see the unique employee

    @artem @search
  Scenario: Search for employee by invalid id
    When user enters invalid employee ID "115601Y" in the ID field
    And user clicks Search button
    Then user is able to see an error message "No Records Found"






