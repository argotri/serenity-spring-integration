package com.gdn.qa.module.ui.testautothon.model;

import lombok.Builder;
import lombok.Data;

/**
 * User: argo.triwidodo
 * Date: 03-Jul-19
 * Time: 11:17
 */

@Data
@Builder
public class PokemonAssertions {
    private Boolean nationalNumber;
    private Boolean weight;
    private Boolean height;
    private Boolean type;
    private Boolean hp;
}
