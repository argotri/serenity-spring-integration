@Pikachu
Feature: Searching pokemon

  Scenario: Searching Some Pokemon on wikipedia and Pokemon db
    Given open a browser
    When user collect pokemon data from wikipedia , PokemonDB and PokeAPI
    Then the data between wikipedia and pokemonDB should be same
    And the data between PokemonDB and pokeApi should be same
    And Generate Rerport

