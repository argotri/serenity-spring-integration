package com.gdn.qa.module.ui.testautothon.data;

import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.model.PokemonResult;
import lombok.Data;
import net.thucydides.core.steps.ScenarioSteps;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * User: argo.triwidodo
 * Date: 26-Jun-19
 * Time: 09:40
 */
@Component
@Data
public class PokemonData{
    private String runMode;
    private List<PokemonResult> pokemonResults;
}
