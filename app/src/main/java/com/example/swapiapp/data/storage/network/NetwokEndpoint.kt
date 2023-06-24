package com.example.swapiapp.data.storage.network

import com.example.swapiapp.data.storage.network.models.PeopleResponse
import com.example.swapiapp.data.storage.network.models.StarshipsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkEndpoint {
    @GET("/api/people/")
    suspend fun getPeopleByName(@Query("search") name: String): PeopleResponse

    @GET("/api/starships/")
    suspend fun getStarshipsByName(@Query("search") name: String): StarshipsResponse
}