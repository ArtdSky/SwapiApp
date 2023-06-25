package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow

class FavoritePeople(
    private val peopleRepository: PeopleRepository
) {

    suspend fun addToFavorite(people: People) {
        return peopleRepository.addPeopleToFavorite(people)

    }

    suspend fun getAllFavorites(): Flow<List<People>> {
        return peopleRepository.getAllFavoritePeople()
    }

    suspend fun deleteByName(name: String){
        return peopleRepository.deletePeopleByName(name)
    }


}