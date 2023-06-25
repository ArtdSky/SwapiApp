package com.example.swapiapp.data.network

import com.example.swapiapp.data.network.models.FilmResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface FilmsEndpoint {
    @GET
    suspend fun fetchData(@Url url: String): FilmResponse
}