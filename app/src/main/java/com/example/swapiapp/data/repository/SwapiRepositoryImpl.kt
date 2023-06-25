package com.example.swapiapp.data.repository

import android.util.Log
import com.example.swapiapp.data.repository.mappers.mapPeopleDataToDomain
import com.example.swapiapp.data.repository.mappers.mapStarshipsDataToDomain
import com.example.swapiapp.data.storage.Storage
import com.example.swapiapp.data.storage.network.ResponseWrapper
import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.domain.repository.SwapiRepository
import kotlinx.coroutines.flow.single

class SwapiRepositoryImpl(
    private val storage: Storage
) : SwapiRepository {
    override suspend fun getPeopleByName(name: String): List<People> {
        return when (val data = storage.getPeopleByName(name).single()) {
            is ResponseWrapper.Success -> {
                val res = mapPeopleDataToDomain( data.value )
                return res
            }
            is ResponseWrapper.NetworkError -> {
                Log.d("NO NETWORK", "NO NETWORK FOR LOAD FoodData")
                emptyList()
            }
            is ResponseWrapper.GenericError -> {
                Log.d("GENERIC DATA", "${data.code}")
                emptyList()
            }
        }
    }

    override suspend fun getStarshipsByName(name: String): List<Starships> {
        return when (val data = storage.getStarshipsByName(name).single()) {
            is ResponseWrapper.Success -> {
                val res = mapStarshipsDataToDomain( data.value )
                return res
            }
            is ResponseWrapper.NetworkError -> {
                Log.d("NO NETWORK", "NO NETWORK FOR LOAD FoodData")
                emptyList()
            }
            is ResponseWrapper.GenericError -> {
                Log.d("GENERIC DATA", "${data.code}")
                emptyList()
            }
        }
    }
}