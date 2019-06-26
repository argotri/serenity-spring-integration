package com.gdn.qa.module.ui.testautothon.pages;

import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.HashMap;

/**
 * User: argo.triwidodo
 * Date: 26-Jun-19
 * Time: 09:34
 */
public class WikipediaPage extends PageObject {

    @FindBy(xpath = "//table//center//abbr")
    WebElementFacade txtNationalNumber;


    public PokemonModel getPokemonData(){
        return PokemonModel.builder().nationalNumber(txtNationalNumber.getText()).build();
    }

}
