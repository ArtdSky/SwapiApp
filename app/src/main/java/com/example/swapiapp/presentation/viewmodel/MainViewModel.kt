package com.example.swapiapp.presentation.viewmodel

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapiapp.data.network.GenericErrorException
import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.domain.usecase.FavoritePeople
import com.example.swapiapp.domain.usecase.FavoriteStarships
import com.example.swapiapp.domain.usecase.GetPeopleByName
import com.example.swapiapp.domain.usecase.GetStarshipsByName
import com.example.swapiapp.presentation.state.ViewState
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val TAG = "VM"

class MainViewModel(
    private val getPeopleByName: GetPeopleByName,
    private val getStarshipsByName: GetStarshipsByName,
    private val favoritePeople: FavoritePeople,
    private val favoriteStarships: FavoriteStarships
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

                Log.d(TAG, people.toString())
                Log.d(TAG, starships.toString())

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
                _viewState.update { currentState -> currentState.copy(isServerError = true) }
                Log.d("TAG_VM", "error : ${e.message}")
            }
        }
    }

    fun addToFavoritePeople(people: People) {
        viewModelScope.launch {
            favoritePeople.addToFavorite(people)
        }
    }

    suspend fun getFavoritePeople(): List<People> {
        return viewModelScope.async {
            favoritePeople.getAllFavorites().single()
        }.await()
    }

    fun addToFavoriteStarship(starship: Starships) {
        viewModelScope.launch {
            favoriteStarships.addToFavorite(starship)
        }
    }

    suspend fun getFavoriteStarships(): List<Starships> {
        return viewModelScope.async {
            favoriteStarships.getAllFavorites().single()
        }.await()
    }

    fun deletePeopleFromFavorites(name : String){
        viewModelScope.launch {
            favoritePeople.deleteByName(name)
        }
    }
    fun deleteStarshipFromFavorites(name : String){
        viewModelScope.launch {
            favoriteStarships.deleteByName(name)
        }
    }
}