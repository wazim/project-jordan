Feature: Project Jordan's Web App
  Scenario: Displays the number of blu rays on the index
    Given Project Jordan is started
    When I go to the Web App
    Then I have a list of 14 Blu Rays

    Scenario: Goes to the Status Page
      Given Project Jordan is started
      When I hit the status page
      Then the response is OK