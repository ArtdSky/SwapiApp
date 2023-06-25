package com.example.swapiapp.domain.usecase

import com.example.swapiapp.domain.models.Film
import com.example.swapiapp.domain.repository.FilmsRepository

class FetchFilm(
    private val filmsRepository: FilmsRepository
) {
    suspend operator fun invoke(url : String) : Film{
        return filmsRepository.fetchFilms(url)
    }
}