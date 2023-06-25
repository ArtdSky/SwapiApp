package com.example.swapiapp.data.storage.starships

import com.example.swapiapp.data.local.model.StarshipEntity

interface StarshipsLocalStorage {

    suspend fun getAllStarships(): List<StarshipEntity>

    suspend fun addNewStarship(starship: StarshipEntity)
}