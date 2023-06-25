package com.example.swapiapp.data.repository.mappers


import com.example.swapiapp.domain.models.People
import com.example.swapiapp.data.storage.network.models.PeopleResponse
import com.example.swapiapp.data.storage.network.models.StarshipsResponse
import com.example.swapiapp.domain.models.Starships

fun mapPeopleDataToDomain(peopleData: PeopleResponse): List<People> {
    return peopleData.results?.map {
        People(
            name = it.name,
            gender = it.gender,
            starshipsCount = it.starships?.size,
            films = it.films
        )
    } ?: emptyList()
}

fun mapStarshipsDataToDomain(starshipsData: StarshipsResponse): List<Starships> {
    return starshipsData.starships?.map {
        Starships(
            name = it.name,
            model = it.model,
            manufacturer = it.manufacturer,
            passengers = it.passengers,
            films = it.films
        )
    } ?: emptyList()
}
