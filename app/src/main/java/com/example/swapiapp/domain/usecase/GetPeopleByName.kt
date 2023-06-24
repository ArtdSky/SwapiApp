package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.repository.SwapiRepository

class GetPeopleByName(
    private val swapiRepository: SwapiRepository
) {
    suspend operator fun invoke(name : String) : List<People> {
        return swapiRepository.getPeopleByName(name)
    }
}