package com.gdn.qa.module.ui.testautothon.utils;

import com.gdn.qa.module.ui.testautothon.webdriver.CustomWebdriver;
import org.openqa.selenium.WebDriver;

/**
 * User: argo.triwidodo
 * Date: 03-Jul-19
 * Time: 08:31
 */
public class WebDriverCreator {

    public static WebDriver createNewSession(WebDriver webDriver){
        CustomWebdriver driver = new CustomWebdriver();
        return driver.newDriver();
    }
}
