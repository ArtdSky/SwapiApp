package com.example.swapiapp.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeopleResponse(
    @Json(name = "count") val count: Int?,
    @Json(name = "next") val next: String?,
    @Json(name = "previous") val previous: String?,
    @Json(name = "results") val results: List<Character>?
)

@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "name") val name: String?,
    @Json(name = "gender") val gender: String?,
    @Json(name = "films") val films: List<String>?,
    @Json(name = "starships") val starships: List<String>?
)
