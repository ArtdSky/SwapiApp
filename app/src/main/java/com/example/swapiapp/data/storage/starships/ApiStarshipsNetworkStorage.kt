package com.example.swapiapp.data.storage.starships

import com.example.swapiapp.data.network.NetworkService
import com.example.swapiapp.data.network.ResponseWrapper
import com.example.swapiapp.data.network.models.StarshipsResponse
import com.example.swapiapp.data.network.safeNetworkCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiStarshipsNetworkStorage : StarshipsNetworkStorage {
    override suspend fun getStarshipsByName(name: String): Flow<ResponseWrapper<StarshipsResponse>> {
        val res :Flow<ResponseWrapper<StarshipsResponse>> = flow {
            val response = safeNetworkCall(Dispatchers.IO){
                NetworkService.swapiService.getStarshipsByName(name)
            }
            emit(response)
        }
        return res
    }
}