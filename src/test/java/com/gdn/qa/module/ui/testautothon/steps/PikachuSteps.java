package com.gdn.qa.module.ui.testautothon.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.data.PokemonData;
import com.gdn.qa.module.ui.testautothon.model.PokemonResult;
import com.gdn.qa.module.ui.testautothon.runnerAutomation.PokemonRunner;
import com.gdn.qa.module.ui.testautothon.steps.serenity.PokemonSteps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.IntegerSyntax;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.gdn.qa.module.ui.testautothon.utils.AssertionsUtil.comparePokemonDbVsPokeAPI;
import static com.gdn.qa.module.ui.testautothon.utils.AssertionsUtil.compareWikipediaVsPokemonDb;
import static com.gdn.qa.module.ui.testautothon.utils.Constant.*;

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
        File file = new File("report", "<directory>/target<file>");
        file.mkdir();
        File fileWikipedia = new File(WIKIPEDIA_FOLDER, "<directory>/target/report<file>");
        fileWikipedia.mkdir();
        File pokemonDb = new File(POKEMONDB_FOLDER, "<directory>/target/report<file>");
        pokemonDb.mkdir();
    }

    @When("^user collect pokemon data from wikipedia , PokemonDB and PokeAPI with '(.*)' thread$")
    public void userCollectPokemonDataFromWikipediaPokemonDBAndPokeAPI(String threadN) throws InterruptedException {
        List<PokemonRunner> pokemonRunners = new ArrayList<>();
        pokemonData.getPokemonResults().forEach((pokemonResult) -> {
            pokemonRunners.add(PokemonRunner.builder()
                    .pokemonResult(pokemonResult).webDriver(pokemonSteps.getDriver())
                    .build());
        });
        Integer thread = threadN.equalsIgnoreCase("UNLIMITED")?pokemonRunners.size(): Integer.valueOf(threadN);
        ExecutorService executorService = Executors.newFixedThreadPool(thread);
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
    }

    @Then("^the data between wikipedia and pokemonDB should be same$")
    public void dataWikiAndPokeDBisSame() {
        pokemonData.getPokemonResults().forEach(pokemonResult -> {
            pokemonResult.setWikiVsPokemonDb(compareWikipediaVsPokemonDb(pokemonResult));
        });
    }

    @Then("^the data between PokemonDB and pokeApi should be same$")
    public void dataPokeDBAndPokeAPIShouldBeSame() {
        pokemonData.getPokemonResults().forEach(pokemonResult -> {
            pokemonResult.setPokemonDbVsPokeApi(comparePokemonDbVsPokeAPI(pokemonResult));
        });
    }

    @When("^Generate Report$")
    public void generateReport() throws IOException {
        // Write data to js data
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(pokemonData);
        jsonString = "var data = " + jsonString + ";";
        FileWriter fw=new FileWriter(System.getProperty("user.dir") + PATH_REPORT +"/data.js");
        fw.write(jsonString);
        fw.close();
        FileUtils.copyFile(new File(System.getProperty("user.dir") + File.separator+"report"+File.separator+"index.html"),new File(System.getProperty("user.dir")+ PATH_REPORT +File.separator+File.separator+ "index.html"));
        FileUtils.copyFile(new File(System.getProperty("user.dir") + File.separator+"report"+File.separator+"logic.js"),new File(System.getProperty("user.dir")+ PATH_REPORT +  File.separator+File.separator+ "logic.js"));
    }

    @Then("validate There are no error")
    public void checkResult() throws Exception{
        for(PokemonResult pokemonResult : pokemonData.getPokemonResults()){
            try{
                if(!pokemonResult.getPokemonDbVsPokeApi().getAllIsTrue() && pokemonResult.getWikiVsPokemonDb().getAllIsTrue()) {
                    throw new Exception("Some data check is failed , please check the report");
                }
            }catch (Exception e){
                throw new Exception("Some data check is failed , please check the report");
            }
        }
    }

}
