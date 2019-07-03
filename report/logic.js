var template = "<div class='col-lg-12'>\n" +
    "            <div>\n" +
    "                <div id=\"pokemonName\"><h3>${pokemonName}</h3></div>\n" +
    "                <table  class=\"table table-dark table-striped\">\n" +
    "                    <thead>\n" +
    "                        <th scope=\"col\"></th>\n" +
    "                        <th scope=\"col\">Wikipedia</th>\n" +
    "                        <th scope=\"col\">PokemonDB</th>\n" +
    "                        <th scope=\"col\">PokeAPI</th>\n" +
    "                        <th scope=\"col\">WIki VS PokemonDB</th>\n" +
    "                        <th scope=\"col\">PokemonDB vs PokemonAPI</th>\n" +
    "                    </thead>\n" +
    "                    <tbody>\n" +
    "                    <tr class='${number}'>\n" +
    "                        <td scope=\"row\">Number</td>\n" +
    "                        <td>${wikipedia.number}</td>\n" +
    "                        <td>${pokemondb.number}</td>\n" +
    "                        <td>${pokeapi.number}</td>\n" +
    "                        <td>${number.wikivspokemondb}</td>\n" +
    "                        <td>${number.pokemondbvspokemonapi}</td>\n" +
    "                    </tr>\n" +
    "                    <tr class='${height}'>\n" +
    "                        <td scope=\"row\">Height (m)</td>\n" +
    "                        <td>${wikipedia.height}</td>\n" +
    "                        <td>${pokemondb.height}</td>\n" +
    "                        <td>${pokeapi.height}</td>\n" +
    "                        <td>${height.wikivspokemondb}</td>\n" +
    "                        <td>${height.pokemondbvspokemonapi}</td>\n" +
    "                    </tr>\n" +
    "                    <tr class='${weight}'>\n" +
    "                        <td scope=\"row\">Weight (kg)</td>\n" +
    "                        <td>${wikipedia.weight}</td>\n" +
    "                        <td>${pokemondb.weight}</td>\n" +
    "                        <td>${pokeapi.weight}</td>\n" +
    "                        <td>${weight.wikivspokemondb}</td>\n" +
    "                        <td>${weight.pokemondbvspokemonapi}</td>\n" +
    "                    </tr>\n" +
    "                    <tr class='${type}'>\n" +
    "                        <td scope=\"row\">Type</td>\n" +
    "                        <td>${wikipedia.type}</td>\n" +
    "                        <td>${pokemondb.type}</td>\n" +
    "                        <td>${pokeapi.type}</td>\n" +
    "                        <td>${type.wikivspokemondb}</td>\n" +
    "                        <td>${type.pokemondbvspokemonapi}</td>\n" +
    "                    </tr>\n" +
    "                    <tr class='${hp}'>\n" +
    "                        <td scope=\"row\">HP</td>\n" +
    "                        <td>-</td>\n" +
    "                        <td>${pokemondb.hp}</td>\n" +
    "                        <td>${pokeapi.hp}</td>\n" +
    "                        <td>-</td>\n" +
    "                        <td>${hp.pokemondbvspokemonapi}</td>\n" +
    "                    </tr>\n" +
    "                    <tr>\n" +
    "                        <td>Pokemon Image</td>\n" +
    "                        <td><img height='100' src=\"${wikipedia.image}\"></td>\n" +
    "                        <td><img height='100' src=\"${pokemondb.image}\"></td>\n" +
    "                        <td>-</td>\n" +
    "                        <td>-</td>\n" +
    "                        <td>-</td>\n" +
    "                    </tr>\n" +
    "                    <tr>\n" +
    "                        <td>Screenshoot</td>\n" +
    "                        <td><a data-fancybox=\"gallery\" href=\"wikipediaReports/${pokemonName}.png\"><img height='100' src=\"wikipediaReports/${pokemonName}.png\"></a></td>\n" +
    "                        <td><a data-fancybox=\"gallery\" href=\"pokemonDbReports/${pokemonName}.png\"><img height='100' src=\"pokemonDbReports/${pokemonName}.png\"></a></td>\n" +
    "                        <td>-</td>\n" +
    "                        <td>-</td>\n" +
    "                        <td>-</td>\n" +
    "                    </tr>\n" +
    "                    </tbody>\n" +
    "                </table>\n" +
    "            </div>\n" +
    "        </div>"

var finializing = "";
data.pokemonResults.forEach(function (result) {


    if(result.dataPokemonDb != null){
        // Number
        var temp = template;
        temp = temp.replace("${pokemonName}",result.name)
        temp = temp.replace("${wikipedia.number}",result.dataWikipedia.nationalNumber)
        temp = temp.replace("${pokemondb.number}",result.dataPokemonDb.nationalNumber)
        temp = temp.replace("${pokeapi.number}",result.dataPokeApi.nationalNumber)
        temp = temp.replace("${number.wikivspokemondb}",renderTrueFalse(result.wikiVsPokemonDb.nationalNumber))
        temp = temp.replace("${number.pokemondbvspokemonapi}",renderTrueFalse(result.pokemonDbVsPokeApi.nationalNumber))

        //height
        temp = temp.replace("${wikipedia.height}",result.dataWikipedia.heightInMeter)
        temp = temp.replace("${pokemondb.height}",result.dataPokemonDb.heightInMeter)
        temp = temp.replace("${pokeapi.height}",result.dataPokeApi.heightInMeter)
        temp = temp.replace("${height.wikivspokemondb}",renderTrueFalse(result.wikiVsPokemonDb.height))
        temp = temp.replace("${height.pokemondbvspokemonapi}",renderTrueFalse(result.pokemonDbVsPokeApi.height))

        //Weight
        temp = temp.replace("${wikipedia.weight}",result.dataWikipedia.weightInKg)
        temp = temp.replace("${pokemondb.weight}",result.dataPokemonDb.weightInKg)
        temp = temp.replace("${pokeapi.weight}",result.dataPokeApi.weightInKg)
        temp = temp.replace("${weight.wikivspokemondb}",renderTrueFalse(result.wikiVsPokemonDb.weight))
        temp = temp.replace("${weight.pokemondbvspokemonapi}",renderTrueFalse(result.pokemonDbVsPokeApi.weight))

        //type
        temp = temp.replace("${wikipedia.type}",result.dataWikipedia.pokemonTypes)
        temp = temp.replace("${pokemondb.type}",result.dataPokemonDb.pokemonTypes)
        temp = temp.replace("${pokeapi.type}",result.dataPokeApi.pokemonTypes)
        temp = temp.replace("${type.wikivspokemondb}",renderTrueFalse(result.wikiVsPokemonDb.type))
        temp = temp.replace("${type.pokemondbvspokemonapi}",renderTrueFalse(result.pokemonDbVsPokeApi.type))

        //HP
        temp = temp.replace("${wikipedia.hp}",result.dataWikipedia.hp)
        temp = temp.replace("${pokemondb.hp}",result.dataPokemonDb.hp)
        temp = temp.replace("${pokeapi.hp}",result.dataPokeApi.hp)
        temp = temp.replace("${hp.pokemondbvspokemonapi}",renderTrueFalse(result.pokemonDbVsPokeApi.hp))

        // Image
        temp = temp.replace("${wikipedia.image}",result.dataWikipedia.imageUrl)
        temp = temp.replace("${pokemondb.image}",result.dataPokemonDb.imageUrl)
        temp = temp.replace("${pokemonName}",result.name)
        temp = temp.replace("${pokemonName}",result.name)
        temp = temp.replace("${pokemonName}",result.name)
        temp = temp.replace("${pokemonName}",result.name)
    }
    else{
        temp = "<div class='alert alert-danger'>The pokemon name <b>"+result.name+"</b> is not found</div>"
    }

    finializing+=temp
})

$("#pokemonDetail").html(finializing)
// $.each(data.pokemonResults,function (presult) {
//     console.log(presult)
// })

function renderTrueFalse(result) {
    if(result){
        return "<img src='https://upload.wikimedia.org/wikipedia/commons/b/b0/Light_green_check.svg' height='30'>"
    }else{
        return "<img src='https://cdn.pixabay.com/photo/2016/03/31/18/31/cancel-1294426_960_720.png' height='30'>"
    }
}