package com.example.swapiapp.presentation.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.swapiapp.domain.models.People
import com.example.swapiapp.domain.models.Starships
import com.example.swapiapp.presentation.navigation.AppDestination
import com.example.swapiapp.presentation.screens.BottomNavigation
import com.example.swapiapp.presentation.screens.StarryBackground
import com.example.swapiapp.presentation.screens.favorites.components.PeopleCard
import com.example.swapiapp.presentation.screens.favorites.components.StarshipsCard
import com.example.swapiapp.presentation.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavHostController,
    currentScreen: AppDestination,
    vm: MainViewModel
) {
    var favoritePeople by remember { mutableStateOf<List<People>>(emptyList()) }
    var favoriteStarships by remember { mutableStateOf<List<Starships>>(emptyList()) }

    LaunchedEffect(Unit) {
        favoritePeople = vm.getFavoritePeople()
        favoriteStarships = vm.getFavoriteStarships()
    }


    Scaffold(
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                StarryBackground(Color.Black)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (favoritePeople.isNotEmpty()) {
                        Text(
                            text = "Пилоты",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.5.sp
                            ),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(favoritePeople.size) { index ->
                            PeopleCard(
                                people = favoritePeople[index],
                                vm = vm,
                                deleteFromFavorite = {
                                    favoritePeople[index].name?.let {
                                        vm.deletePeopleFromFavorites(
                                            it
                                        )
                                    }
                                    favoritePeople = favoritePeople.filter { it != favoritePeople[index] }
                                }
                            )
                        }
                    }

                    if (favoriteStarships.isNotEmpty()) {
                        Text(
                            text = "Звездолеты",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.5.sp
                            ),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(favoriteStarships.size) { index ->
                            StarshipsCard(
                                starship = favoriteStarships[index],
                                deleteFromFavorite = {
                                    favoriteStarships[index].name?.let {
                                        vm.deleteStarshipFromFavorites(
                                            it
                                        )
                                    }
                                    favoriteStarships = favoriteStarships.filter { it != favoriteStarships[index] }
                                }
                            )
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