package com.example.swapiapp.data.storage.people

import com.example.swapiapp.data.local.model.PeopleEntity

interface PeopleLocalStorage  {
    suspend fun getAllPeople(): List<PeopleEntity>

    suspend fun addNewPeople(people: PeopleEntity)

    suspend fun deletePeopleByName(name: String)

}