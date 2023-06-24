package com.example.swapiapp.data.repository

import com.example.swapiapp.data.storage.Storage
import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.domain.repository.SwapiRepository

class SwapiRepositoryImpl(
    private val storage: Storage
) : SwapiRepository {
    override suspend fun getPeopleByName(name: String): List<People> {
        return storage.getPeopleByName(name)
    }

    override suspend fun getStarshipsByName(name: String): List<Starships> {
        return storage.getStarshipsByName(name)
    }
}