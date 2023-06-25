package com.example.swapiapp.data.local

import com.example.swapiapp.data.local.model.PeopleEntity
import com.example.swapiapp.data.local.model.StarshipEntity

class AppRepository(
    private val peopleDao: PeopleDao,
    private val starshipsDao: StarshipsDao
) {
    suspend fun getAllPeople(): List<PeopleEntity>{
        return peopleDao.getAllPeople()
    }

    suspend fun addNewPeople(people: PeopleEntity){
        return peopleDao.addNewPeople(people)
    }

    suspend fun getAllStarships(): List<StarshipEntity>{
        return starshipsDao.getAllStarships()
    }


    suspend fun addNewStarship(starship: StarshipEntity){
        return starshipsDao.addNewStarship(starship)
    }


}