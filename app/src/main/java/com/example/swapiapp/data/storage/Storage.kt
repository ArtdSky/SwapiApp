package com.example.swapiapp.data.storage

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships

interface Storage {

    suspend fun getPeopleByName(name : String) : List<People>

    suspend fun getStarshipsByName(name : String) : List<Starships>
}