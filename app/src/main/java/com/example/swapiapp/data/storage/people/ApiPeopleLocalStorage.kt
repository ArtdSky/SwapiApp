package com.example.swapiapp.data.storage.people

import com.example.swapiapp.data.local.AppRepository
import com.example.swapiapp.data.local.model.PeopleEntity

class ApiPeopleLocalStorage(
    private val appRepository: AppRepository
) : PeopleLocalStorage {
    override suspend fun getAllPeople(): List<PeopleEntity> {
        return appRepository.getAllPeople()
    }

    override suspend fun addNewPeople(people: PeopleEntity) {
        return appRepository.addNewPeople(people)
    }
}