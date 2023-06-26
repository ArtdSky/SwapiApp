package com.example.swapiapp.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmResponse(
    @Json(name = "title") val title: String,
    @Json(name = "director") val director: String,
    @Json(name = "producer") val producer: String
)
