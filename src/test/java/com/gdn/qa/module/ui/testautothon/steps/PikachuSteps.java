package com.gdn.qa.module.ui.testautothon.steps;

import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.data.PokemonData;
import com.gdn.qa.module.ui.testautothon.model.PokemonResult;
import com.gdn.qa.module.ui.testautothon.runnerAutomation.PokemonRunner;
import com.gdn.qa.module.ui.testautothon.steps.serenity.PokemonSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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


  /*  @Given("^User on Google homepage$")
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
    }*/

    @Given("^open a browser$")
    public void openABrowser() {
//        System.out.println("Pokemon Data" + pokemonData.getWikipediaDatas());
//        System.out.println("Pokemon Data" + pokemonData.getPokemonDbUiDatas());

    }

    @When("^user collect pokemon data from wikipedia , PokemonDB and PokeAPI$")
    public void userCollectPokemonDataFromWikipediaPokemonDBAndPokeAPI() throws InterruptedException {
        List<PokemonRunner> pokemonRunners = new ArrayList<>();
        pokemonData.getPokemonResults().forEach((pokemonResult) -> {
            pokemonRunners.add(PokemonRunner.builder()
                    .pokemonResult(pokemonResult).webDriver(pokemonSteps.getDriver())
                    .build());
        });
        ExecutorService executorService = Executors.newFixedThreadPool(pokemonRunners.size());
        List<Future<PokemonResult>> result = executorService.invokeAll(pokemonRunners);
        AtomicBoolean isFailed = new AtomicBoolean(false);
        List<PokemonResult> resultTest = new ArrayList<>();
        result.stream().forEach(tr -> {
            try {
                if (tr.isDone()) {
                    resultTest.add(tr.get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        List<Boolean> done = result.stream().map(Future::isDone).collect(Collectors.toList());

        while (true) {
            if (!executorService.isShutdown()) {
                int size = done.stream()
                        .filter(aBoolean -> aBoolean.equals(true))
                        .collect(Collectors.toList())
                        .size();
                if (size == done.size()) {
                    System.out.println("Stopping executor service...");
                    executorService.shutdownNow();
                    break;
                }
            }
        }
        System.out.println("Hasil Pokemon Number " + resultTest.toString());
    }

    @Then("^the data should be same$")
    public void theDataShouldBeSame() {
    }
}
