package com.gdn.qa.module.ui.testautothon;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/consult_dictionary/",tags = {"@Pikachu"})
public class DefinitionTestSuite {
//    @Rule
//    public SpringIntegrationMethodRule springIntegrationMethodRule = new SpringIntegrationMethodRule();

}
