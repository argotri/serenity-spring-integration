package com.gdn.qa.module.ui.testautothon.webdriver;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
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
        }else if (variables.getProperty("browser.type").equalsIgnoreCase("REMOTE")){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            capabilities.setVersion("75.0");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);

            try {
                return new RemoteWebDriver(
                        URI.create("http://10.148.0.12:4444/wd/hub").toURL(),
                        capabilities
                );
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return new ChromeDriver();
    }

    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
