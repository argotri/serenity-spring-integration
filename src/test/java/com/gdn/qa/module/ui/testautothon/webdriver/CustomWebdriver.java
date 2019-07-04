package com.gdn.qa.module.ui.testautothon.webdriver;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * User: argo.triwidodo
 * Date: 04-Jul-19
 * Time: 09:43
 */
public class CustomWebdriver implements DriverSource {
    @Override
    public WebDriver newDriver() {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        if(variables.getProperty("browser.type").equalsIgnoreCase("CHROME")){
            System.out.println("Run in Chrome");

            return new ChromeDriver();
        }else if (variables.getProperty("browser.type").equalsIgnoreCase("MOBILE")){
            System.out.println("Run in Mobile");
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "Nexus 5");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            chromeOptions.addArguments("--lang=id");
            return new ChromeDriver(chromeOptions);
        }
        return new ChromeDriver();
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
