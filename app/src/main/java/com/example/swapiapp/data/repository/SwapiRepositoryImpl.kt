package com.example.swapiapp.data.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.swapiapp.data.repository.mappers.mapPeopleDataToDomain
import com.example.swapiapp.data.repository.mappers.mapStarshipsDataToDomain
import com.example.swapiapp.data.storage.people.ApiPeopleNetworkStorage
import com.example.swapiapp.data.storage.starships.ApiStarshipsNetworkStorage
import com.example.swapiapp.data.network.GenericErrorException
import com.example.swapiapp.data.network.ResponseWrapper
import com.example.swapiapp.data.storage.people.ApiPeopleLocalStorage
import com.example.swapiapp.data.storage.starships.ApiStarshipsLocalStorage
import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.domain.repository.SwapiRepository
import kotlinx.coroutines.flow.single

class SwapiRepositoryImpl(
    private val apiPeopleNetworkStorage: ApiPeopleNetworkStorage,
    private val apiStarshipsNetworkStorage: ApiStarshipsNetworkStorage,
    private val apiPeopleLocalStorage: ApiPeopleLocalStorage,
    private val apiStarshipsLocalStorage: ApiStarshipsLocalStorage
) : SwapiRepository {
    override suspend fun getAllFavoritePeople(): List<People> {
        TODO("Not yet implemented")
    }

    override suspend fun addPeopleToFavorite(people: People) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFavoriteStarships(): List<Starships> {
        TODO("Not yet implemented")
    }

    override suspend fun addStarshipToFavorite(starship: Starships) {
        TODO("Not yet implemented")
    }

    override suspend fun getPeopleByName(name: String): List<People> {
        when (val data = apiPeopleNetworkStorage.getPeopleByName(name).single()) {
            is ResponseWrapper.Success -> {
                val res = mapPeopleDataToDomain(data.value)
                return res
            }

            is ResponseWrapper.NetworkError -> {
                throw NetworkErrorException("No network connection")
            }

            is ResponseWrapper.GenericError -> {
                throw GenericErrorException("Generic http error occurred")
            }
        }
    }

    override suspend fun getStarshipsByName(name: String): List<Starships> {
        return when (val data = apiStarshipsNetworkStorage.getStarshipsByName(name).single()) {
            is ResponseWrapper.Success -> {
                val res = mapStarshipsDataToDomain(data.value)
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