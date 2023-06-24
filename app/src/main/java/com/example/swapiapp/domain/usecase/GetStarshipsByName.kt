package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.domain.repository.SwapiRepository

class GetStarshipsByName(
    private val swapiRepository: SwapiRepository
) {
    suspend operator fun invoke(name: String): List<Starships> {
        return swapiRepository.getStarshipsByName(name)
    }
}