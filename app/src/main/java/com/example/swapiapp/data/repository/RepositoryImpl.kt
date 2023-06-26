package com.example.swapiapp.data.repository

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.swapiapp.data.network.GenericErrorException
import com.example.swapiapp.data.network.NetworkService
import com.example.swapiapp.data.network.ResponseWrapper
import com.example.swapiapp.data.network.safeNetworkCall
import com.example.swapiapp.data.repository.mappers.mapEntityToPeople
import com.example.swapiapp.data.repository.mappers.mapEntityToStarships
import com.example.swapiapp.data.repository.mappers.mapPeopleDataToDomain
import com.example.swapiapp.data.repository.mappers.mapPeopleToEntity
import com.example.swapiapp.data.repository.mappers.mapStarshipsDataToDomain
import com.example.swapiapp.data.repository.mappers.mapStarshipsToEntity
import com.example.swapiapp.data.storage.people.ApiPeopleLocalStorage
import com.example.swapiapp.data.storage.people.ApiPeopleNetworkStorage
import com.example.swapiapp.data.storage.starships.ApiStarshipsLocalStorage
import com.example.swapiapp.data.storage.starships.ApiStarshipsNetworkStorage
import com.example.swapiapp.domain.models.Film
import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.domain.repository.FilmsRepository
import com.example.swapiapp.domain.repository.PeopleRepository
import com.example.swapiapp.domain.repository.StarshipsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single

class RepositoryImpl(
    private val apiPeopleNetworkStorage: ApiPeopleNetworkStorage,
    private val apiStarshipsNetworkStorage: ApiStarshipsNetworkStorage,
    private val apiPeopleLocalStorage: ApiPeopleLocalStorage,
    private val apiStarshipsLocalStorage: ApiStarshipsLocalStorage
) : PeopleRepository, StarshipsRepository, FilmsRepository {
    override suspend fun getAllFavoritePeople(): Flow<List<People>> {
        return flow {
            val peopleList = apiPeopleLocalStorage.getAllPeople().map { entity ->
                mapEntityToPeople(entity)
            }
            emit(peopleList)
        }
    }

    override suspend fun deletePeopleByName(name: String) {
        return apiPeopleLocalStorage.deletePeopleByName(name)
    }

    override suspend fun deleteStarshipByName(name: String) {
        return apiStarshipsLocalStorage.deleteStarshipByName(name)
    }

    override suspend fun addPeopleToFavorite(people: People) {
        val peopleEntity = mapPeopleToEntity(people)
        return apiPeopleLocalStorage.addNewPeople(peopleEntity)
    }

    override suspend fun getAllFavoriteStarships(): Flow<List<Starships>> {
        return flow {
            val starshipList = apiStarshipsLocalStorage.getAllStarships().map {
                mapEntityToStarships(it)
            }
            emit(starshipList)
        }
    }

    override suspend fun addStarshipToFavorite(starship: Starships) {
        val starshipEntity = mapStarshipsToEntity(starship)
        return apiStarshipsLocalStorage.addNewStarship(starshipEntity)
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
        when (val data = apiStarshipsNetworkStorage.getStarshipsByName(name).single()) {
            is ResponseWrapper.Success -> {
                val res = mapStarshipsDataToDomain(data.value)
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

    override suspend fun fetchFilms(url: String): Film {
        val response = flow {
            val response = safeNetworkCall(Dispatchers.IO) {
                NetworkService.filmsService.fetchData(url)
            }
            emit(response)
        }
        when (val data = response.single()) {
            is ResponseWrapper.Success -> {
                return Film(
                    title = data.value.title,
                    director = data.value.director,
                    producer = data.value.producer
                )
            }
            is ResponseWrapper.NetworkError -> {
                throw NetworkErrorException("No network connection")
            }

            is ResponseWrapper.GenericError -> {
                throw GenericErrorException("Generic http error occurred")
            }
        }
    }
}