package com.example.swapiapp.domain.repository

import com.example.swapiapp.domain.models.Starships
import kotlinx.coroutines.flow.Flow

interface StarshipsRepository {

    suspend fun getStarshipsByName(name: String): List<Starships>

    suspend fun getAllFavoriteStarships(): Flow<List<Starships>>

    suspend fun addStarshipToFavorite(starship: Starships)

    suspend fun deleteStarshipByName(name: String)
}