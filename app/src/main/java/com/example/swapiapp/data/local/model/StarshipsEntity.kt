package com.example.swapiapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "starships_table")
data class StarshipEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String? = null,
    val model: String? = null,
    val manufacturer: String? = null,
    val passengers: Int? = null,
    @ColumnInfo(name = "films")
    val films: List<String>? = null
)
