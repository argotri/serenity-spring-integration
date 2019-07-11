package com.gdn.qa.module.ui.testautothon;

import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import org.junit.Rule;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * User: argo.triwidodo
 * Date: 26-Jun-19
 * Time: 10:13
 */
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.gdn.qa")
public class AppConfig {
    @Rule
    public SpringIntegrationMethodRule springIntegrationMethodRule = new SpringIntegrationMethodRule();
}
