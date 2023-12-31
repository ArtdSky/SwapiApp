package com.example.swapiapp.presentation.state

import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships

data class ViewState(
    val isServerError: Boolean = false,
    val isNoNetworkError: Boolean = false,
    val loading: Boolean = false,
    val people: List<People>? = null,
    val starships: List<Starships>? = null,
)