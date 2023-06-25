package com.example.swapiapp.data.storage.people

import com.example.swapiapp.data.network.NetworkService
import com.example.swapiapp.data.network.ResponseWrapper
import com.example.swapiapp.data.network.models.FilmResponse
import com.example.swapiapp.data.network.models.PeopleResponse
import com.example.swapiapp.data.network.safeNetworkCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiPeopleNetworkStorage : PeopleNetworkStorage {
    override suspend fun getPeopleByName(name: String): Flow<ResponseWrapper<PeopleResponse>> {
        return flow {
            val response = safeNetworkCall(Dispatchers.IO) {
                NetworkService.swapiService.getPeopleByName(name)
            }
            emit(response)
        }
    }

}