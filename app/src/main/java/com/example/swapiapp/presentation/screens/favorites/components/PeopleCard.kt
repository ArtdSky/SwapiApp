package com.example.swapiapp.presentation.screens.favorites.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.swapiapp.domain.models.Film
import com.example.swapiapp.domain.models.People
import com.example.swapiapp.presentation.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleCard(
    people: People,
    vm: MainViewModel,
    deleteFromFavorite: () -> Unit
) {

    val films = remember { mutableStateListOf<Film?>() }
    val isLoading = remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    if (isExpanded) {
        isLoading.value = true
        people.films?.forEach { film ->
            vm.fetchFilmData(film) { fetchedFilm ->
                films.add(fetchedFilm)
            }
        }
        isLoading.value = false
    } else {
        films.clear()
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF386BEB),
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            if (!isLoading.value) {
                isExpanded = !isExpanded
            }
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Имя: ${people.name}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Пол: ${people.gender}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Пилотировал звездолетов: ${people.starshipsCount}")
                Spacer(modifier = Modifier.height(8.dp))
                if (isExpanded) {

                    films.forEach { film ->
                        if (film != null) {
                            Text(text = "Фильм: ${film.title}")
                        }
                    }
                } else {
                    Text(text = "Фильмы: ${people.films?.size}")
                }
            }
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete from Favorites",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        deleteFromFavorite()
                    }
            )
        }
    }
}

