package com.example.swapiapp.data.storage.starships

import com.example.swapiapp.data.network.ResponseWrapper
import com.example.swapiapp.data.network.models.StarshipsResponse
import kotlinx.coroutines.flow.Flow

interface StarshipsNetworkStorage {

    suspend fun getStarshipsByName(name : String) : Flow<ResponseWrapper<StarshipsResponse>>

}