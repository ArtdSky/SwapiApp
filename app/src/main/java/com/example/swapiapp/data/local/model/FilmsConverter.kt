package com.example.swapiapp.data.local.model

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class FilmsConverter {
    private val moshi = Moshi.Builder().build()
    private val type = Types.newParameterizedType(List::class.java, String::class.java)
    private val adapter = moshi.adapter<List<String>>(type)

    @TypeConverter
    fun fromFilmsList(films: List<String>?): String? {
        return adapter.toJson(films)
    }

    @TypeConverter
    fun toFilmsList(filmsString: String?): List<String>? {
        return adapter.fromJson(filmsString)
    }
}