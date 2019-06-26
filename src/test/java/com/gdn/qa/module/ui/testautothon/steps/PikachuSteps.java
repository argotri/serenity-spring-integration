package com.gdn.qa.module.ui.testautothon.steps;

import com.gdn.qa.module.ui.testautothon.AppConfig;
import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.data.PokemonData;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.steps.serenity.PokemonSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;

/**
 * User: argo.triwidodo
 * Date: 24-Jun-19
 * Time: 14:42
 */

@BlibliSteps
public class PikachuSteps {

    @Steps
    PokemonSteps pokemonSteps;

    @Autowired
    private PokemonData pokemonData;

    @Given("^User on Google homepage$")
    public void userOnGoogleHomepage() {
        pokemonSteps.userOpenGoogleHomePage();
    }

    @When("^user type \"([^\"]*)\" on google$")
    public void userTypeOnGoogle(String keyword) throws Throwable {
        pokemonSteps.searchOnGoogle(keyword);
    }

    @And("^user go to wikipedia from that pokemon$")
    public void userGoToWikipediaFromThatPokemon() {
        pokemonSteps.gotoWikipediaPageFromGoogle();
    }

    @And("^user get data \"([^\"]*)\" from wikipedia page$")
    public void userGetDataFromWikipediaPage(String pokemonName) {
        if(pokemonData.getWikipediaDatas() == null){
            pokemonData.setWikipediaDatas(new HashMap<>());
        }
//        pokemonData.getWikipediaDatas().put(pokemonName, PokemonModel.builder().nationalNumber("250").build());
        pokemonData.getWikipediaDatas().put(pokemonName , pokemonSteps.getWikipediaData());
    }

    @Then("^pokemon number should be same between wikipedia and pokemon db$")
    public void pokemonNumberShouldBeSameBetweenWikipediaAndPokemonDb() {
        System.out.println(pokemonData.getWikipediaDatas().toString());
    }
}
