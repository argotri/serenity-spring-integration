package com.gdn.qa.module.ui.testautothon.steps.serenity;

import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.data.PokemonData;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.pages.WikipediaPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.beans.factory.annotation.Autowired;

@BlibliSteps
public class WikipediaSteps extends ScenarioSteps {

    @Step("Get Data From Wikipedia")
    public void getDataFromWikipedia(String pokemonName , PokemonModel pokemonModel){
        System.out.println("Pokemon Model in steps " + pokemonModel + " | " + pokemonName);
        getDriver().get(pokemonModel.getUrl());
    }
}
