package com.example.swapiapp.data.storage.starships

import com.example.swapiapp.data.local.AppRepository
import com.example.swapiapp.data.local.model.StarshipEntity

class ApiStarshipsLocalStorage(
    private val appRepository: AppRepository
) : StarshipsLocalStorage {

    override suspend fun getAllStarships(): List<StarshipEntity> {
        return appRepository.getAllStarships()
    }

    override suspend fun addNewStarship(starship: StarshipEntity) {
        return appRepository.addNewStarship(starship)
    }
}