package com.inaki.starwarsmvpapp.rest

import com.inaki.starwarsmvpapp.model.characters.Characters
import io.reactivex.Single
import retrofit2.http.GET

interface StarWarsApi {

    // THis GET will retrieve all the characters from star wars
    @GET(STAR_WARS_PEOPLE)
    fun retrieveCharacters(): Single<Characters>

    // TODO add the model for the planets and add the return type
    @GET(STAR_WARS_PLANETS)
    fun retrievePlanets()

    // TODO add the model for the starships and add the return type
    @GET(STAR_WARS_STARSHIPS)
    fun retrieveStarships()

    companion object {
        // adding the URL paths for the star wars api
        private const val STAR_WARS_PEOPLE = "api/people"
        private const val STAR_WARS_PLANETS = "api/planets"
        private const val STAR_WARS_STARSHIPS = "api/starships"

        // base url for star wars
        const val BASE_URL = "https://www.swapi.tech/"
    }

}