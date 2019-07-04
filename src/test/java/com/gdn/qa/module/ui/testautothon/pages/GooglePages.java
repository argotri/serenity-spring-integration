package com.gdn.qa.module.ui.testautothon.pages;

import com.gdn.qa.module.ui.testautothon.configuration.DriverProperties;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

/**
 * User: argo.triwidodo
 * Date: 24-Jun-19
 * Time: 14:34
 */

@Component("com.gdn.qa.module.ui.testautothon.pages")
@DefaultUrl("https://www.google.co.id/")
public class GooglePages extends PageObject {

    @Autowired
    DriverProperties driverProperties;

    @FindBy(name = "q")
    public WebElementFacade txtSearchGoogle;

    @FindBy(id = "rhs_block")
    private WebElementFacade featuredBlock;

    public String getWikipediaUrl()throws Exception{
        String link = "";
        if(!driverProperties.isMobile()){
            link = featuredBlock
                    .withTimeoutOf(10, ChronoUnit.SECONDS)
                    .waitUntilPresent()
                    .find(By.xpath("//div[@id=\"rhs_block\"]//a[contains(@href,\"wikipedia.org\")]"))
                    .getAttribute("href");
        }else{
            link = find(By.xpath("(//a[contains(@href,\"wikipedia.org\")])[1]"))
                    .withTimeoutOf(10,ChronoUnit.SECONDS)
                    . getAttribute("href");
        }
        return link;
    }
    public String getPokemonDbUrl() throws Exception{
        String link = "";
        if(!driverProperties.isMobile()){
            link = featuredBlock
                    .withTimeoutOf(10, ChronoUnit.SECONDS)
                    .waitUntilPresent()
                    .find(By.xpath("//div[@class=\"r\"]//a[contains(@href,\"pokemondb.net\")]"))
                    .getAttribute("href");
        }else{
            link = find(By.xpath("(//a[contains(@data-amp-cur,\"https://pokemondb.net/pokedex/\")])[1]"))
                            .withTimeoutOf(10,ChronoUnit.SECONDS)
                    .getAttribute("data-amp-cur");
        }
        return link;
    }

    public void typeSearchOnGoogle(String query){
        txtSearchGoogle.typeAndEnter(query);
    }

}
