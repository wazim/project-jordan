Feature: Project Jordan Hits Amazon
  Scenario: It is time to retrieve BluRays from Amazon
    Given I have started jordan
    When Jordan goes to amazon
    Then I should have 14 blu rays