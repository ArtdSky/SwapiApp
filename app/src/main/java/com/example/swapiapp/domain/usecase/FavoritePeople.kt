package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.repository.SwapiRepository

class FavoritePeople(
    private val swapiRepository: SwapiRepository
) {

    suspend fun addToFavorite(people: People) {
        return swapiRepository.addPeopleToFavorite(people)

    }

    suspend fun getAllFavorites(): List<People> {
        return swapiRepository.getAllFavoritePeople()
    }


}