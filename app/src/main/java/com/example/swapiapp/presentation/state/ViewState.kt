package com.example.swapiapp.presentation.state

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships

data class ViewState (
    val people : List<People>? = null,
    val starships: List<Starships>? = null
)