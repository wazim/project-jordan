Feature: Project Jordan's Web App

  Scenario: Displays the number of blu rays on the index
    Given Project Jordan is started
    When I go to the Web App
    Then I have a list of 1 Blu Rays

  Scenario: Goes to the Status Page
    Given Project Jordan is started
    When I hit the status page
    Then the response is OK

  Scenario: Goes to the API page
    Given Project Jordan is started
    When I hit the API page
    Then the response should contain "<name>The Godfather</name>"

  Scenario: Emails a user when a BluRay becomes available
    Given Project Jordan is started with no primed response
    Given The email server is started
    When The user requests an email for when "Dances With Wolves" becomes available to "test@email.com"
    Given "Dances With Wolves" becomes available
    Then The user receives an email