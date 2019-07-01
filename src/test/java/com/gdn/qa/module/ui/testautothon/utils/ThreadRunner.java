package com.gdn.qa.module.ui.testautothon.utils;
import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.pages.GooglePages;
import com.gdn.qa.module.ui.testautothon.steps.serenity.WikipediaSteps;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class ThreadRunner implements Runnable {
    private String name;
    Thread thread;
    PokemonModel pokemonModel;
    WebDriver webDriver;

    public ThreadRunner(String name , PokemonModel pokemonModel , WebDriver webDriver){
        this.name = name;
        this.pokemonModel = pokemonModel;
        this.webDriver = webDriver;
        System.out.println("Pokemon model thread " +name + " | "+ pokemonModel);
    }

    @Override
    public void run() {
        System.out.println("Tread Name " + name);
        new WikipediaSteps().getDataFromWikipedia(name , pokemonModel,webDriver);
    }

    public void start(){
        System.out.println("Starting " + name);
        if(thread== null){
            thread = new Thread(this,name);
            thread.start();
        }
    }
}
