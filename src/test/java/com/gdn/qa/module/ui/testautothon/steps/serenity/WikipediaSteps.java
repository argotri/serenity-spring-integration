package com.gdn.qa.module.ui.testautothon.steps.serenity;

import com.gdn.qa.module.ui.testautothon.annotation.BlibliSteps;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@BlibliSteps
public class WikipediaSteps extends ScenarioSteps {

    @Step("Get Data From Wikipedia")
    public String getDataFromWikipedia(PokemonModel pokemonModel, WebDriver webdriver) {
        System.out.println("Pokemon Model in steps " + pokemonModel );
        webdriver.get(pokemonModel.getUrl());
        WebDriverWait wait = new WebDriverWait(webdriver , 10);
        WebElement elementNationalNumber = wait.until(ExpectedConditions.visibilityOf(webdriver.findElement(By.xpath("//table[@class='infobox']//th//table//tbody//tr//center//b"))));
        String nationalNumber = elementNationalNumber.getText();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        webdriver.close();
        return nationalNumber;
    }
}
