@Pikachu
Feature: Searching pokemon

  Scenario Outline: Searching Some Pokemon on wikipedia
    Given User on Google homepage
    When user type "<pokemonName>" on google
    And user go to wikipedia from that pokemon
    And user get data "<pokemonName>" from wikipedia page
    Then pokemon number should be same between wikipedia and pokemon db
    
    Examples:
      | pokemonName |
      | Pikachu     |
      | Charizard   |
