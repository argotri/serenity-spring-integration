package com.gdn.qa.module.ui.testautothon.configuration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

/**
 * User: argo.triwidodo
 * Date: 26-Jun-19
 * Time: 11:16
 */
@Component("com.gdn.qa.module.ui.testautothon.configuration")
public class DriverProperties extends PageObject {


    public Boolean isMobile() {
        return (getDriver() instanceof AndroidDriver) || (getDriver() instanceof AppiumDriver) || SystemEnvironmentVariables.createEnvironmentVariables().getProperty("browser.type").equalsIgnoreCase("mobile");
    }
}
