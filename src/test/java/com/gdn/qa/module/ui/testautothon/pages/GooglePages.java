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
@DefaultUrl("https://www.google.com/")
public class GooglePages extends PageObject {

    @Autowired
    DriverProperties driverProperties;

    @FindBy(name = "q")
    public WebElementFacade txtSearchGoogle;

    @FindBy(id = "rhs_block")
    private WebElementFacade featuredBlock;


    public void clickWikipediaLinkOnFeature(){
        if(!driverProperties.isMobile()){
            featuredBlock
                    .withTimeoutOf(10, ChronoUnit.SECONDS)
                    .waitUntilPresent()
                    .find(By.xpath("//div[@id=\"rhs_block\"]//a[contains(@href,\"wikipedia.org\")]"))
                    .click();
        }else{
            find(By.xpath("//g-flippy-carousel//a[contains(@href,\"wikipedia.org\")]"))
                    .withTimeoutOf(10,ChronoUnit.SECONDS)
                    .click();
        }
    }

    public void typeSearchOnGoogle(String query){
        txtSearchGoogle.typeAndEnter(query);
    }
}
