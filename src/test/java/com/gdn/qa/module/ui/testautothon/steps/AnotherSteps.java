package com.gdn.qa.module.ui.testautothon.steps;

import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.data.PokemonData;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: argo.triwidodo
 * Date: 26-Jun-19
 * Time: 10:28
 */

@BlibliSteps
public class AnotherSteps {

    @Autowired
    PokemonData pokemonData;

    @Given("Test Another Steps")
    public void test(){
        pokemonData.toString();
    }
}
