package com.gdn.qa.module.ui.testautothon.model.pokeapiResult.stats;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * User: argo.triwidodo
 * Date: 03-Jul-19
 * Time: 09:42
 */
@Data

public class PokeApiStats {
    public Integer baseStat;
    public Integer effort;
    public Stat stat;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
