package com.example.swapiapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.swapiapp.domain.usecase.GetPeopleByName
import com.example.swapiapp.domain.usecase.GetStarshipsByName

class MainViewModel(
    private val getPeopleByName: GetPeopleByName,
    private val getStarshipsByName: GetStarshipsByName
) : ViewModel() {
}