package com.gdn.qa.module.ui.testautothon.runnerAutomation;

import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.steps.serenity.WikipediaSteps;
import lombok.Builder;
import lombok.Data;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.Callable;

/**
 * User: argo.triwidodo
 * Date: 02-Jul-19
 * Time: 10:20
 */

@Data
@Builder
public class PokemonRunner implements Callable<String> {

    private PokemonModel pokemonModel;
    private WebDriver webDriver;

    @Override
    public String call() throws Exception {
        WikipediaSteps wikipediaSteps = new WikipediaSteps();
        System.out.println("Test lalala " + pokemonModel.toString());
        Thread.sleep(10000);
        return wikipediaSteps.getDataFromWikipedia(pokemonModel,webDriver);
    }

}
