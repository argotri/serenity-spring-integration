package com.gdn.qa.module.ui.testautothon.utils;
import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.pages.GooglePages;
import com.gdn.qa.module.ui.testautothon.steps.serenity.WikipediaSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("com.gdn.qa.module.ui.testautothon.utils")
public class ThreadRunner implements Runnable {
    private String name;
    PokemonModel pokemonModel;
    public void setData(String name , PokemonModel pokemonModel){
        this.name = name;
        this.pokemonModel = pokemonModel;
        System.out.println("Pokemon model thread " +name + " | "+ pokemonModel);
    }

    @Override
    public void run() {
        System.out.println("Tread Name " + name);
        new WikipediaSteps().getDataFromWikipedia(name , pokemonModel);
    }
}
