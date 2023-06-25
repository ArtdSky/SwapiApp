package com.example.swapiapp.domain.repository

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships

interface SwapiRepository {

    suspend fun getPeopleByName(name : String) : List<People>

    suspend fun getStarshipsByName(name : String) : List<Starships>

    suspend fun getAllFavoritePeople(): List<People>

    suspend fun addPeopleToFavorite(people: People)

    suspend fun getAllFavoriteStarships(): List<Starships>

    suspend fun addStarshipToFavorite(starship: Starships)
}