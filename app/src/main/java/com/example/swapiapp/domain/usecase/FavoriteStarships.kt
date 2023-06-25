package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.domain.repository.PeopleRepository
import com.example.swapiapp.domain.repository.StarshipsRepository
import kotlinx.coroutines.flow.Flow

class FavoriteStarships(
    private val starshipsRepository: StarshipsRepository
) {

    suspend fun addToFavorite(starship: Starships) {
        return starshipsRepository.addStarshipToFavorite(starship)

    }

    suspend fun getAllFavorites(): Flow<List<Starships>> {
        return starshipsRepository.getAllFavoriteStarships()
    }

    suspend fun deleteByName(name: String){
        return starshipsRepository.deleteStarshipByName(name)
    }

}