package com.gdn.qa.module.ui.testautothon.model;

import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import lombok.Builder;
import lombok.Data;

/**
 * User: argo.triwidodo
 * Date: 03-Jul-19
 * Time: 08:35
 */
@Data
@Builder
public class PokemonResult {
    private String name;
    private PokemonModel dataWikipedia;
    private PokemonModel dataPokemonDb;
    private PokemonModel dataPokeApi;
    private PokemonAssertions wikiVsPokemonDb;
    private PokemonAssertions pokemonDbVsPokeApi;
}
