package com.example.swapiapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swapiapp.data.local.model.PeopleEntity

@Dao
interface PeopleDao {

    @Query("SELECT * FROM people_table")
    suspend fun getAllPeople(): List<PeopleEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewPeople(people: PeopleEntity)

    @Query("DELETE FROM people_table WHERE name = :name")
    suspend fun deletePeopleByName(name: String)
}