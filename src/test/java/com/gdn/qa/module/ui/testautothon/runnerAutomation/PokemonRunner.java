package com.gdn.qa.module.ui.testautothon.runnerAutomation;

import com.gdn.qa.module.ui.testautothon.model.PokemonResult;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.steps.serenity.GetDataSteps;
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
public class PokemonRunner implements Callable<PokemonResult> {

    private PokemonResult pokemonResult;
    private WebDriver webDriver;

    @Override
    public PokemonResult call() throws Exception {
        GetDataSteps getDataSteps = new GetDataSteps();
        System.out.println("Test lalala " + pokemonResult.toString());
        return getDataSteps.getData(pokemonResult,webDriver);
    }

}
