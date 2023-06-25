package com.example.swapiapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swapiapp.data.local.model.StarshipEntity

@Dao
interface StarshipsDao {

    @Query("SELECT * FROM starships_table")
    suspend fun getAllStarships(): List<StarshipEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewStarship(starship: StarshipEntity)

    @Query("DELETE FROM starships_table WHERE name = :name")
    suspend fun deleteStarshipByName(name: String)
}