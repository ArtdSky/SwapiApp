package com.example.swapiapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "people_table")
data class PeopleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String? = null,
    val gender: String? = null,
    val starshipsCount: Int? = null,
    @ColumnInfo(name = "films")
    val films: List<String>? = null
)
