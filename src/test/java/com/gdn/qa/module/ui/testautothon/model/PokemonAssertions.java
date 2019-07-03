package com.gdn.qa.module.ui.testautothon.model;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

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
    public Boolean getAllIsTrue(){
        return nationalNumber && weight && height && type && Optional.ofNullable(hp).orElse(true);
    }
}
