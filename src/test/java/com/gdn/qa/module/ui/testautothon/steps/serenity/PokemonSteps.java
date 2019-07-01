package com.gdn.qa.module.ui.testautothon.steps.serenity;

import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.pages.GooglePages;
import com.gdn.qa.module.ui.testautothon.pages.WikipediaPage;
import lombok.Data;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.stereotype.Component;

/**
 * User: argo.triwidodo
 * Date: 25-Jun-19
 * Time: 14:53
 */

@Data
@Component
public class PokemonSteps extends ScenarioSteps {

    private GooglePages googlePages;
    private WikipediaPage wikipediaPage;


    @Step("User Open Google Home Page")
    public void userOpenGoogleHomePage() {
        googlePages.open();
    }

    @Step("User type {String}")
    public void searchOnGoogle(String keyword) {
        googlePages.typeSearchOnGoogle(keyword);
    }
    @Step("User go to wikipedia page")
    public void gotoWikipediaPageFromGoogle(){
        googlePages.getWikipediaUrl();
    }

    @Step("User get data pokemon from wikipedia page")
    public PokemonModel getWikipediaData(){
        return wikipediaPage.getPokemonData();
    }
}
