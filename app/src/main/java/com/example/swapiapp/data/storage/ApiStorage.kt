package com.example.swapiapp.data.storage

import com.example.swapiapp.data.storage.network.NetworkService
import com.example.swapiapp.data.storage.network.ResponseWrapper
import com.example.swapiapp.data.storage.network.models.PeopleResponse
import com.example.swapiapp.data.storage.network.models.StarshipsResponse
import com.example.swapiapp.data.storage.network.safeNetworkCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiStorage : Storage {
    override suspend fun getPeopleByName(name: String): Flow<ResponseWrapper<PeopleResponse>> {
        val res : Flow<ResponseWrapper<PeopleResponse>> = flow {
            val response = safeNetworkCall(Dispatchers.IO){
                NetworkService.swapiService.getPeopleByName(name)
            }
            emit(response)
        }
        return res
    }

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