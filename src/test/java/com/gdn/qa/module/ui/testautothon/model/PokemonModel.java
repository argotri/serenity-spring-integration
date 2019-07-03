package com.gdn.qa.module.ui.testautothon.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * User: argo.triwidodo
 * Date: 26-Jun-19
 * Time: 09:36
 */

@Data
@Builder
public class PokemonModel {
    private String pokemonName;
    private String url;
    private String nationalNumber;
    private String imageUrl;
    private String nameInJapanese;
    private Float heightInMeter;
    private Float weightInKg;
    private List<String> pokemonTypes;
    private Integer hp;
}
