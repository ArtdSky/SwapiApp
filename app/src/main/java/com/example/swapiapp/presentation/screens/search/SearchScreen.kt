package com.example.swapiapp.presentation.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.swapiapp.presentation.navigation.AppDestination
import com.example.swapiapp.presentation.screens.BottomNavigation
import com.example.swapiapp.presentation.screens.StarryBackground
import com.example.swapiapp.presentation.screens.search.components.PeopleCard
import com.example.swapiapp.presentation.screens.search.components.StarshipsCard
import com.example.swapiapp.presentation.screens.search.components.SearchBar
import com.example.swapiapp.presentation.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    currentScreen: AppDestination,
    vm: MainViewModel
) {
    val state by vm.viewState.collectAsState()

    Scaffold(
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                StarryBackground(Color.Black)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    SearchBar(vm = vm)
                    if (state.isNoNetworkError) {
                        Text(text = "No network")
                    } else if (state.isServerError) {
                        Text(text = "Oups, got a server error")
                    } else {
                        if (state.loading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        } else {
                            if (state.people != null) Text(
                                text = "Пилоты",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    letterSpacing = 0.5.sp
                                ),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            LazyColumn(
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                state.people?.let { people ->
                                    items(people.size) { index ->
                                        PeopleCard(
                                            name = people[index].name ?: "Unknown",
                                            gender = people[index].gender ?: "Unknown",
                                            starshipsCount = people[index].starshipsCount ?: 0,
                                            addToFavorite = {
                                                vm.addToFavoritePeople(
                                                    people = people[index]
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                            if (state.starships != null) Text(
                                text = "Звездолеты",
                                style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    letterSpacing = 0.5.sp
                                ),
                                modifier = Modifier.padding(bottom = 4.dp)
                            )
                            LazyColumn(
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                state.starships?.let { starships ->
                                    items(starships.size) { index ->
                                        StarshipsCard(
                                            name = starships[index].name ?: "Unknown",
                                            model = starships[index].model ?: "Unknown",
                                            manufactured = starships[index].manufacturer ?: "Unknown",
                                            passengers = starships[index].passengers ?: "0",
                                            addToFavorite = {
                                                vm.addToFavoriteStarship(
                                                    starship = starships[index]
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }

                }

            }
        },
        bottomBar = {
            BottomNavigation(
                currentScreen = currentScreen,
                navController = navController
            )
        }
    )
}



