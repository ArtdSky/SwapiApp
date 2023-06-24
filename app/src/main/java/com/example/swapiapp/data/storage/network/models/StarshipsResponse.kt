package com.example.swapiapp.data.storage.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StarshipsResponse(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val starships: List<Starship>?
)

@JsonClass(generateAdapter = true)
data class Starship(
    @Json(name = "name")
    val name: String?,
    @Json(name = "model")
    val model: String?,
    @Json(name = "manufacturer")
    val manufacturer: String?,
    @Json(name = "passengers")
    val passengers: String?,
    @Json(name = "films")
    val films: List<String>?
)
