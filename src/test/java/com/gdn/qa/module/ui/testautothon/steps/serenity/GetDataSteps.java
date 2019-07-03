package com.gdn.qa.module.ui.testautothon.steps.serenity;

import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.model.PokemonResult;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

import static com.gdn.qa.module.ui.testautothon.utils.WebDriverCreator.createNewSession;


@BlibliSteps
public class GetDataSteps extends ScenarioSteps {
    private PokemonResult pokemonResult;

    @Step("Initiate Get data")
    public PokemonResult getData(PokemonResult pokemonResult, WebDriver webDriver) {
        webDriver = createNewSession(webDriver);
        pokemonResult.
                setDataWikipedia(getDataFromWikipedia(pokemonResult.getDataWikipedia(), webDriver));
        pokemonResult.setDataPokemonDb(getDataFromPokemonDb(pokemonResult.getDataPokemonDb(), webDriver));
        System.out.println("Hasil Result " + pokemonResult);
        webDriver.close();
        return pokemonResult;
    }

    @Step("Get Data From Wikipedia")
    private PokemonModel getDataFromWikipedia(PokemonModel pokemonModel, WebDriver webdriver) {
        System.out.println("Pokemon Model in steps " + pokemonModel);
        webdriver.get(pokemonModel.getUrl());
        WebDriverWait wait = new WebDriverWait(webdriver, 10);
        pokemonModel.setNationalNumber(wait.until(ExpectedConditions.visibilityOf(webdriver.findElement(By.xpath("//table[@class='infobox']//th//table//tbody//tr//center//b")))).getText());
        pokemonModel.setImageUrl(wait.until(ExpectedConditions.visibilityOf(webdriver.findElement(By.xpath("//div[@class='floatnone']//a[@class='image']/img")))).getAttribute("src"));
        // click tampilkan
        wait.until(ExpectedConditions.visibilityOf(webdriver.findElement(By.xpath("//a[@id='collapseButton0']")))).click();
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
    private PokemonModel getDataFromPokemonDb(PokemonModel pokemonModel, WebDriver webdriver) {
        System.out.println("Pokemon Model in steps " + pokemonModel);
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
    private PokemonModel getDataFromPokemonApi() {
        given()
    }
}
