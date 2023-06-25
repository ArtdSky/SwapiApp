package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.domain.repository.StarshipsRepository

class GetStarshipsByName(
    private val starshipsRepository: StarshipsRepository
) {
    suspend operator fun invoke(name: String): List<Starships> {
        return starshipsRepository.getStarshipsByName(name)
    }
}