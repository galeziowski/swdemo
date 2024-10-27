Feature: Owner
  Background: I need an admin user
    Given I setup admin user

  Scenario: 001 - Get owner by Id
    Given I have a user with name "Marta" and role "ADMIN"
    When I read the OwnerProfile with id of user "Marta"
    Then I receive a correct response
    Then Owner has name set to "Marta"
    And Owner has permissions set to "ADMIN"

  Scenario: 002 - Get owners list
    Given I have a user with name "Zyta" and role "ADMIN"
    When Get owners
    Then I receive a correct response
    And Owners list has owner with name "Zyta"
