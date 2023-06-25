package com.example.swapiapp.data.storage.people

import com.example.swapiapp.data.network.ResponseWrapper
import com.example.swapiapp.data.network.models.PeopleResponse
import kotlinx.coroutines.flow.Flow

interface PeopleNetworkStorage {

    suspend fun getPeopleByName(name : String) : Flow<ResponseWrapper<PeopleResponse>>
}