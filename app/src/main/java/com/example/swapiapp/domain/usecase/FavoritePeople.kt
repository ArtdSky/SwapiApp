package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.repository.SwapiRepository
import kotlinx.coroutines.flow.Flow

class FavoritePeople(
    private val swapiRepository: SwapiRepository
) {

    suspend fun addToFavorite(people: People) {
        return swapiRepository.addPeopleToFavorite(people)

    }

    suspend fun getAllFavorites(): Flow<List<People>> {
        return swapiRepository.getAllFavoritePeople()
    }


}