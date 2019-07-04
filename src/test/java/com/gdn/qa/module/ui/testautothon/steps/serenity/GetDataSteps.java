package com.gdn.qa.module.ui.testautothon.steps.serenity;

import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.configuration.DriverProperties;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.model.PokemonResult;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.gdn.qa.module.ui.testautothon.utils.Constant.*;
import static com.gdn.qa.module.ui.testautothon.utils.WebDriverCreator.createNewSession;
import static io.restassured.RestAssured.get;


@BlibliSteps
public class GetDataSteps extends ScenarioSteps {
    private PokemonResult pokemonResult;
    private DriverProperties driverProperties;

    @Step("Initiate Get data")
    public PokemonResult getData(PokemonResult pokemonResult, WebDriver webDriver) {
        webDriver = createNewSession(webDriver);
        driverProperties = new DriverProperties();
        try {
            pokemonResult.
                    setDataWikipedia(getDataFromWikipedia(pokemonResult.getDataWikipedia(), webDriver));
            pokemonResult.setDataPokemonDb(getDataFromPokemonDb(pokemonResult.getDataPokemonDb(), webDriver));
            pokemonResult.setDataPokeApi(getDataFromPokemonApi(pokemonResult.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        webDriver.close();
        return pokemonResult;
    }

    @Step("Get Data From Wikipedia")
    private PokemonModel getDataFromWikipedia(PokemonModel pokemonModel, WebDriver webdriver) throws Exception {
        webdriver.get(pokemonModel.getUrl());
        webdriver.manage().window().maximize();
        File src= ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src,new File(System.getProperty("user.dir")  + PATH_REPORT + "/"+WIKIPEDIA_FOLDER + "/"+pokemonModel.getPokemonName().toLowerCase()+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebDriverWait wait = new WebDriverWait(webdriver, 10);
        pokemonModel.setNationalNumber(wait.until(ExpectedConditions.visibilityOf(webdriver.findElement(By.xpath("//table[@class='infobox']//th//table//tbody//tr//center//b")))).getText());
        pokemonModel.setImageUrl(wait.until(ExpectedConditions.visibilityOf(webdriver.findElement(By.xpath("//div[@class='floatnone']//a[@class='image']/img")))).getAttribute("src"));
        // click tampilkan
        try{
            wait.until(ExpectedConditions.visibilityOf(webdriver.findElement(By.xpath("//a[@id='collapseButton0']")))).click();
        }catch (Exception e){

        }
        pokemonModel.setNameInJapanese(webdriver.findElement(By.xpath("//th/a[contains(@title,\"Bahasa Jepang\")]/ancestor::tr[1]/td")).getText());
        // get weight
        String weightPure = webdriver.findElement(By.xpath("//th[contains(text(),'Berat')]/ancestor::tr[1]/td")).getText();
        pokemonModel.setWeightInKg(Float.valueOf(weightPure.split("kg")[0].trim().replace(",", ".")));
        // Get Height
        String heightPure = webdriver.findElement(By.xpath("//th[contains(text(),'Tinggi')]/ancestor::tr[1]/td")).getText();
        pokemonModel.setHeightInMeter(Float.valueOf(heightPure.split("m")[0].trim().replace(",", ".")));
        // Get Tipes
        String tipesPure = webdriver.findElement(By.xpath("//th[contains(text(),'Tipe')]/ancestor::tr[1]/td")).getText();
        pokemonModel.setPokemonTypes(Arrays.asList(tipesPure.split(", ")));
        return pokemonModel;
    }

    @Step("Get Data From PokemonDb")
    private PokemonModel getDataFromPokemonDb(PokemonModel pokemonModel, WebDriver webdriver)  throws Exception {
        webdriver.get(pokemonModel.getUrl());
        webdriver.manage().window().maximize();
        File src= ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src,new File(System.getProperty("user.dir")  + PATH_REPORT + "/"+POKEMONDB_FOLDER + "/"+pokemonModel.getPokemonName().toLowerCase()+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        webdriver.get(pokemonModel.getUrl());
        WebDriverWait wait = new WebDriverWait(webdriver, 10);
        pokemonModel.setNationalNumber(wait.until(ExpectedConditions.visibilityOf(webdriver.findElement(By.xpath("//table[@class='vitals-table']//th[contains(text(),'National №')]//ancestor::tr/td")))).getText());
        pokemonModel.setImageUrl(wait.until(ExpectedConditions.visibilityOf(webdriver.findElement(By.xpath("//a[@rel='lightbox']/img")))).getAttribute("src"));
        pokemonModel.setNameInJapanese(webdriver.findElement(By.xpath("//th[contains(text(),\"Japanese\")]/ancestor::tr[1]/td")).getText());
        // get weight
        String weightPure = webdriver.findElement(By.xpath("//th[contains(text(),'Weight')]/ancestor::tr[1]/td")).getText();
        pokemonModel.setWeightInKg(Float.valueOf(weightPure.split("kg")[0].trim().replace(",", ".")));
        // Get Height
        String heightPure = webdriver.findElement(By.xpath("//th[contains(text(),'Height')]/ancestor::tr[1]/td")).getText();
        pokemonModel.setHeightInMeter(Float.valueOf(heightPure.split("m")[0].trim()));
        // Get Tipes
        String tipesPure = webdriver.findElement(By.xpath("(//th[contains(text(),'Type')]/ancestor::tr[1]/td)[1]")).getText();
        pokemonModel.setPokemonTypes(Arrays.asList(tipesPure.split(" ")));
        // Get HP
        pokemonModel.setHp(Integer.valueOf(webdriver.findElement(By.xpath("(//th[text() = 'HP']/ancestor::tr/td)[1]")).getText()));
        return pokemonModel;
    }

    @Step("Get Dta from PokemonAPI")
    private PokemonModel getDataFromPokemonApi(String pokemonName) throws Exception {
        PokemonModel pokemonModel = PokemonModel.builder().build();
        pokemonModel.setPokemonName(pokemonName);
        pokemonModel.setUrl("https://pokeapi.co/api/v2/pokemon/" + pokemonName);
        Response response = get(pokemonModel.getUrl());
        if(response.statusCode()==200){
            JsonPath jsonPath = new JsonPath(response.asString());
            pokemonModel.setHp((Integer) ((List<HashMap<String,Object>>)jsonPath.get("stats.findAll {stats -> stats.stat.name == 'hp'}")).get(0).get("base_stat"));
            pokemonModel.setNationalNumber(Integer.toString(jsonPath.get("id")));
            List<HashMap<String,Object>> types =jsonPath.getList("types");
            List<String> typesInString = types.stream().map(s -> ((HashMap<String,String>)s.get("type")).get("name")).collect(Collectors.toList());
            pokemonModel.setPokemonTypes(typesInString);
            pokemonModel.setHeightInMeter(jsonPath.getFloat("height")/10);
            pokemonModel.setWeightInKg(jsonPath.getFloat("weight")/10);
        }
        return pokemonModel;
    }
}
