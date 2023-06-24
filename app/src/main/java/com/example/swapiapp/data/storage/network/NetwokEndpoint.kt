package com.example.swapiapp.data.storage.network

import com.example.swapiapp.data.storage.network.models.PeopleResponse
import com.example.swapiapp.data.storage.network.models.StarshipsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkEndpoint {

    @GET("/people/?search={name}")
    suspend fun getPeopleByName(@Path("name") name: String): PeopleResponse

    @GET("/starships/?search={name}")
    suspend fun getStarshipsByName(@Path("name") name: String): StarshipsResponse
}