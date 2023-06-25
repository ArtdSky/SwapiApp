package com.example.swapiapp.presentation.viewmodel

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapiapp.data.storage.network.GenericErrorException
import com.example.swapiapp.domain.usecase.GetPeopleByName
import com.example.swapiapp.domain.usecase.GetStarshipsByName
import com.example.swapiapp.presentation.state.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPeopleByName: GetPeopleByName,
    private val getStarshipsByName: GetStarshipsByName
) : ViewModel() {

    private val _viewState = MutableStateFlow(
        ViewState()
    )
    val viewState = _viewState.asStateFlow()


    fun getPeople(name: String) {
        viewModelScope.launch {
            _viewState.update { currentState -> currentState.copy(loading = true) }
            try {
                val people = getPeopleByName(name)
                val starships = getStarshipsByName(name)

                _viewState.update { currentState ->
                    currentState.copy(
                        loading = false,
                        people = people,
                        starships = starships
                    )
                }
            } catch (e: NetworkErrorException) {
                _viewState.update { currentState -> currentState.copy(isNoNetworkError = true) }
                Log.d("TAG_VM", "error : ${e.message}")
            } catch (e: GenericErrorException) {
                _viewState.update { currentState -> currentState.copy(isServerError  = true) }
                Log.d("TAG_VM", "error : ${e.message}")
            }
        }
    }

}