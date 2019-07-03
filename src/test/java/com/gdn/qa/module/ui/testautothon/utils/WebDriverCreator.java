package com.gdn.qa.module.ui.testautothon.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * User: argo.triwidodo
 * Date: 03-Jul-19
 * Time: 08:31
 */
public class WebDriverCreator {

    public static WebDriver createNewSession(WebDriver webDriver){
        if(webDriver instanceof FirefoxDriver){
            return new FirefoxDriver();
        }else if (webDriver instanceof ChromeDriver){
            return new ChromeDriver();
        }else{
            return new ChromeDriver();
        }
    }
}
