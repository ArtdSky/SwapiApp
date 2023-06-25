package com.example.swapiapp.data.repository.mappers


import com.example.swapiapp.data.local.model.PeopleEntity
import com.example.swapiapp.data.local.model.StarshipEntity
import com.example.swapiapp.domain.models.People
import com.example.swapiapp.data.network.models.PeopleResponse
import com.example.swapiapp.data.network.models.StarshipsResponse
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

fun mapPeopleToEntity(people: People): PeopleEntity {
    return PeopleEntity(
        name = people.name,
        gender = people.gender,
        starshipsCount = people.starshipsCount,
        films = people.films
    )
}

fun mapEntityToPeople(entity: PeopleEntity): People {
    return People(
        name = entity.name,
        gender = entity.gender,
        starshipsCount = entity.starshipsCount,
        films = entity.films
    )
}

fun mapStarshipsToEntity(starships: Starships): StarshipEntity {
    return StarshipEntity(
        name = starships.name,
        model = starships.model,
        manufacturer = starships.manufacturer,
        passengers = starships.passengers?.toIntOrNull(),
        films = starships.films
    )
}

fun mapEntityToStarships(starshipEntity: StarshipEntity): Starships {
    return Starships(
        name = starshipEntity.name,
        model = starshipEntity.model,
        manufacturer = starshipEntity.manufacturer,
        passengers = starshipEntity.passengers?.toString(),
        films = starshipEntity.films
    )
}


