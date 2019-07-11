package com.gdn.qa.module.ui.testautothon;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
//@ExtendWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/consult_dictionary/", tags = {"@Pikachu"})
public class DefinitionTestSuite {

}