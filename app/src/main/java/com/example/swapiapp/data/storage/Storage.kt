package com.example.swapiapp.data.storage

import com.example.swapiapp.data.storage.network.ResponseWrapper
import com.example.swapiapp.data.storage.network.models.PeopleResponse
import com.example.swapiapp.data.storage.network.models.StarshipsResponse
import kotlinx.coroutines.flow.Flow

interface Storage {

    suspend fun getPeopleByName(name : String) : Flow<ResponseWrapper<PeopleResponse>>

    suspend fun getStarshipsByName(name : String) : Flow<ResponseWrapper<StarshipsResponse>>
}