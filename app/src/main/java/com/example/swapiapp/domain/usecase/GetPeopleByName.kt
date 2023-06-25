package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.repository.PeopleRepository

class GetPeopleByName(
    private val peopleRepository: PeopleRepository
) {
    suspend operator fun invoke(name: String): List<People> {
        return peopleRepository.getPeopleByName(name)
    }
}