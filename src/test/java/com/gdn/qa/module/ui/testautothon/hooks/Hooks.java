package com.gdn.qa.module.ui.testautothon.hooks;

import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.data.PokemonData;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.pages.GooglePages;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.extern.log4j.Log4j;
import net.serenitybdd.core.pages.PageObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@BlibliSteps
public class Hooks {

    @Autowired
    PokemonData pokemonData;

    @Autowired
    GooglePages googlePages;

    @Before
    public void populateDataPokemon() throws IOException {
        List<PokemonModel> wikipediaModels = new ArrayList<>();
        List<PokemonModel> pokemonDbModels = new ArrayList<>();
        List<String> pokemonNames = readData();
        googlePages.open();
        pokemonNames.forEach(s -> {
            googlePages.typeSearchOnGoogle(s);
            wikipediaModels.add(PokemonModel.builder().pokemonName(s.toLowerCase()).url(googlePages.getWikipediaUrl()).build());
            googlePages.typeSearchOnGoogle(s +" PokemonDB");
            pokemonDbModels.add(PokemonModel.builder().pokemonName(s.toLowerCase()).url(googlePages.getPokemonDbUrl()).build());
        });
        googlePages.getDriver().close();
        pokemonData.setWikipediaDatas(wikipediaModels);
        pokemonData.setPokemonDbUiDatas(pokemonDbModels);
    }

    @After
    public void after(){

    }

    private List<String> readData() throws IOException {
        String file = System.getProperty("user.dir") + File.separator +"src"+File.separator+"test"+File.separator+"resources"+File.separator+"data" +File.separator+ "pokemonData.csv";
        System.out.println("File Read " + file);
        List<String> content = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                content.add(line);
            }
        } catch (FileNotFoundException e) {
            //Some error logging
        }
        return content;
    }


}
