package com.gdn.qa.module.ui.testautothon.hooks;

import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.data.PokemonData;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.model.PokemonResult;
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
        List<PokemonResult> pokemonResults = new ArrayList<>();
//        List<PokemonModel> wikipediaModels = new ArrayList<>();
//        List<PokemonModel> pokemonDbModels = new ArrayList<>();
        List<String> pokemonNames = readData();
        googlePages.open();
        pokemonNames.forEach(s -> {
            googlePages.typeSearchOnGoogle(s);
            PokemonModel dataWiki = PokemonModel.builder().pokemonName(s.toLowerCase()).url(googlePages.getWikipediaUrl()).build();
            googlePages.typeSearchOnGoogle(s +" PokemonDB");
            PokemonModel dataPokemonDb = PokemonModel.builder().pokemonName(s.toLowerCase()).url(googlePages.getPokemonDbUrl()).build();
            pokemonResults.add(
                    PokemonResult.builder()
                            .dataWikipedia(dataWiki)
                            .dataPokemonDb(dataPokemonDb)
                            .name(s.toLowerCase())
                            .build()
            );
        });
        pokemonData.setPokemonResults(pokemonResults);
        googlePages.getDriver().close();
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
