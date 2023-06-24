package com.example.swapiapp.domain.repository

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships

interface SwapiRepository {

    suspend fun getPeopleByName(name : String) : List<People>

    suspend fun getStarshipsByName(name : String) : List<Starships>
}