package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.domain.repository.SwapiRepository
import kotlinx.coroutines.flow.Flow

class FavoriteStarships(
    private val swapiRepository: SwapiRepository
) {

    suspend fun addToFavorite(starship: Starships) {
        return swapiRepository.addStarshipToFavorite(starship)

    }

    suspend fun getAllFavorites(): Flow<List<Starships>> {
        return swapiRepository.getAllFavoriteStarships()
    }


}