package com.gdn.qa.module.ui.testautothon.utils;

import com.gdn.qa.module.ui.testautothon.model.PokemonAssertions;
import com.gdn.qa.module.ui.testautothon.model.PokemonModel;
import com.gdn.qa.module.ui.testautothon.model.PokemonResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * User: argo.triwidodo
 * Date: 03-Jul-19
 * Time: 11:13
 */

public class AssertionsUtil {

    public static PokemonAssertions compareWikipediaVsPokemonDb(PokemonResult pokemonResult) {
        PokemonModel wikipediaModel = pokemonResult.getDataWikipedia();
        PokemonModel pokedbModel = pokemonResult.getDataPokemonDb();
        if (wikipediaModel.getNationalNumber() != null) {
            return PokemonAssertions.builder()
                    .nationalNumber(wikipediaModel.getNationalNumber().equalsIgnoreCase(pokedbModel.getNationalNumber()))
                    .height(wikipediaModel.getHeightInMeter().equals(pokedbModel.getHeightInMeter()))
                    .weight(wikipediaModel.getWeightInKg().equals(pokedbModel.getWeightInKg()))
                    .type(comparePokemonTypes(Optional.ofNullable(wikipediaModel.getPokemonTypes()).orElse(new ArrayList<>()), Optional.ofNullable(pokedbModel.getPokemonTypes()).orElse(new ArrayList<>()), true))
                    .build();
        } else {
            return null;
        }

    }

    public static PokemonAssertions comparePokemonDbVsPokeAPI(PokemonResult pokemonResult) {
        PokemonModel pokedbModel = pokemonResult.getDataPokemonDb();
        PokemonModel pokeApi = pokemonResult.getDataPokeApi();
        if (pokedbModel != null) {
            try{
                return PokemonAssertions.builder()
                        .nationalNumber(pokeApi.getNationalNumber().equalsIgnoreCase(pokedbModel.getNationalNumber()))
                        .height(pokeApi.getHeightInMeter().equals(pokedbModel.getHeightInMeter()))
                        .weight(pokeApi.getWeightInKg().equals(pokedbModel.getWeightInKg()))
                        .type(comparePokemonTypes(pokeApi.getPokemonTypes(), pokedbModel.getPokemonTypes(), false))
                        .hp(pokeApi.getHp().equals(pokedbModel.getHp()))
                        .build();
            }catch (Exception e){
                return null;
            }
        } else {
            return null;
        }
    }


    private static Boolean comparePokemonTypes(List<String> indonesianTypes, List<String> englishTypes, Boolean needConvert) {

        indonesianTypes = (needConvert) ? indonesianTypes.stream().map(s -> convertTypeIndonesianToEnglish(s.toLowerCase())).collect(Collectors.toList()) : indonesianTypes;
        englishTypes = englishTypes.stream().map(s -> s.toLowerCase()).collect(Collectors.toList());
        Collections.sort(englishTypes);
        Collections.sort(indonesianTypes);
        System.out.println("Type " + indonesianTypes + " vs " + englishTypes);
        return CollectionUtils.isEqualCollection(indonesianTypes, englishTypes);
    }

    public static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);
        return sb.toString();
    }

    private static String convertTypeIndonesianToEnglish(String indonesianType) {
        HashMap<String, String> dictionary = new HashMap<>();
        dictionary.put("listrik", "electric");
        dictionary.put("api", "fire");
        dictionary.put("terbang", "flying");
        dictionary.put("psikis", "psychic");
        dictionary.put("racun", "poison");
        dictionary.put("rumput", "grass");
        dictionary.put("air", "water");
        if (dictionary.get(indonesianType.toLowerCase()) != null) {
            return dictionary.get(indonesianType.toLowerCase());
        } else {
            return indonesianType;
        }
    }

}
