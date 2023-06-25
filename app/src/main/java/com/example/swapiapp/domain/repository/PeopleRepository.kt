package com.example.swapiapp.domain.repository

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    suspend fun getPeopleByName(name : String) : List<People>

    suspend fun deletePeopleByName(name: String)

    suspend fun getAllFavoritePeople(): Flow<List<People>>

    suspend fun addPeopleToFavorite(people: People)



}