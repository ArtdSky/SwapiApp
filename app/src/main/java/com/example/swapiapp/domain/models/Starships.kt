package com.example.swapiapp.domain.models

data class Starships(
    val name : String?,
    val model : String?,
    val manufacturer : String?,
    val passengers : String?,
    val films : List<String>?
)
