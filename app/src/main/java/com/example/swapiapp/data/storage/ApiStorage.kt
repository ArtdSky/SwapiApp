package com.example.swapiapp.data.storage

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships

class ApiStorage : Storage {
    override suspend fun getPeopleByName(name: String): List<People> {
        TODO("Not yet implemented")
    }

    override suspend fun getStarshipsByName(name: String): List<Starships> {
        TODO("Not yet implemented")
    }
}