package com.example.swapiapp.domain.repository

import com.example.swapiapp.domain.models.Film

interface FilmsRepository {

    suspend fun fetchFilms(url : String) : Film
}