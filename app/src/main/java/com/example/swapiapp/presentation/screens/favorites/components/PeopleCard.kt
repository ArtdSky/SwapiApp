package com.example.swapiapp.presentation.screens.favorites.components

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.swapiapp.domain.models.People

@Composable
fun PeopleCard(
    people: People,
    deleteFromFavorite: () -> Unit
) {
    val isFavorite = remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF386BEB),
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(4.dp)
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
                Text(text = "Фильмы: ${people.films?.size}")
            }
            Icon(
                imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Delete from Favorites",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        isFavorite.value = !isFavorite.value
                        deleteFromFavorite()
                    }
            )
        }
    }
}
