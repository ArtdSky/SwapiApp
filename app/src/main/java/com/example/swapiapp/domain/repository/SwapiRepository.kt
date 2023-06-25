package com.example.swapiapp.domain.repository

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships
import kotlinx.coroutines.flow.Flow

interface SwapiRepository {

    suspend fun getPeopleByName(name : String) : List<People>

    suspend fun getStarshipsByName(name : String) : List<Starships>

    suspend fun getAllFavoritePeople(): Flow<List<People>>

    suspend fun addPeopleToFavorite(people: People)

    suspend fun getAllFavoriteStarships(): Flow<List<Starships>>

    suspend fun addStarshipToFavorite(starship: Starships)

    suspend fun deletePeopleByName(name: String)

    suspend fun deleteStarshipByName(name: String)

}