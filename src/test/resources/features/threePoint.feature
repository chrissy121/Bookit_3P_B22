Feature: Verify the info from all environment


  Scenario: Get the info from UI
    Given User logs in with "sbirdbj@fc2.com" and "asenorval"
    When User navigates to mySelf page
    Then User gets the UI info

  Scenario: Get the info from API
    When User logs in BookIT API using "sbirdbj@fc2.com" and "asenorval"
    Then User gets API information

  @wip @db
  Scenario: Get the info from DB
    When User sends a query to DataBase with "sbirdbj@fc2.com"
    Then User gets DataBase information
