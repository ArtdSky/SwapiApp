package com.example.swapiapp.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.swapiapp.R
import com.example.swapiapp.presentation.navigation.AppDestination
import com.example.swapiapp.presentation.screens.components.PeopleCard
import com.example.swapiapp.presentation.screens.components.StarshipsCard
import com.example.swapiapp.presentation.viewmodel.MainViewModel
import kotlin.random.Random

@Composable
fun SearchScreen(
    navController: NavHostController,
    currentScreen: AppDestination,
    vm: MainViewModel
) {
    val state by vm.viewState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        StarryBackground()
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
                                    name = people[index].name ?: "",
                                    gender = people[index].gender ?: "",
                                    starshipsCount = people[index].starshipsCount ?: 0
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
                                    name = starships[index].name ?: "",
                                    model = starships[index].model ?: "",
                                    manufactured = starships[index].manufacturer ?: "",
                                    passengers = starships[index].passengers ?: "0"
                                )
                            }
                        }
                    }
                }
            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    vm: MainViewModel
) {
    val searchQuery = remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            value = searchQuery.value,
            onValueChange = { newValue ->
                searchQuery.value = newValue
                if (newValue.length > 1) {
                    vm.getPeople(newValue)
                }
            },
            label = { Text(text = "Search") }
        )
    }
}

@Composable
fun StarryBackground() {
    val random = Random(System.currentTimeMillis())

    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(color = Color.Black)

        repeat(100) {
            val x : Float = random.nextInt(size.width.toInt()).toFloat()
            val y : Float = random.nextInt(size.height.toInt()).toFloat()
            val radius : Float = random.nextFloat() + 1f
            val color = Color.White

            drawCircle(
                color = color,
                center = Offset(x, y),
                radius = radius.dp.toPx(),
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}

