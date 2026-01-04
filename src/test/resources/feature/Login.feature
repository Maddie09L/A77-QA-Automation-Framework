Feature: Login Feature

  Scenario: Positive Login Test
    Given I open koel Login Page
    When I enter email "madeleiny.mason@testpro.io"
    And I enter password "FU2nVt8d"
    And I click submit
    Then I am logged in