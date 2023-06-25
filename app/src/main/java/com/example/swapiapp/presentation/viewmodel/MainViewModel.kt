package com.example.swapiapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapiapp.domain.usecase.GetPeopleByName
import com.example.swapiapp.domain.usecase.GetStarshipsByName
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPeopleByName: GetPeopleByName,
    private val getStarshipsByName: GetStarshipsByName
) : ViewModel() {

    init {
        getPeople("uk")
    }
    fun getPeople(name: String) {
        viewModelScope.launch {
            getPeopleByName(name)
            getStarshipsByName(name)
        }
    }

}