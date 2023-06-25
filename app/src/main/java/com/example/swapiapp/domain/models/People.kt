package com.example.swapiapp.domain.models

data class People(
    val name : String?,
    val gender : String?,
    val starshipsCount : Int?,
    val films : List<String>?
)

