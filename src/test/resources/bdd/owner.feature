Feature: Owner

  Scenario: 001 - Get owner
    When I read the OwnerProfile with id "1"
    Then I receive a correct response
    Then Owner has name set to "Łukasz"
    And Owner has permissions set to "ADMIN"
