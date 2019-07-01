package com.gdn.qa.module.ui.testautothon.model;

import lombok.Builder;
import lombok.Data;

/**
 * User: argo.triwidodo
 * Date: 26-Jun-19
 * Time: 09:36
 */

@Data
@Builder
public class PokemonModel {
    private String nationalNumber;
    private String url;
}
