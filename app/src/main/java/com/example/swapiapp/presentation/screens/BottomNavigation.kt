package com.example.swapiapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.swapiapp.presentation.navigation.AppDestination
import com.example.swapiapp.presentation.navigation.FavoritesScreen
import com.example.swapiapp.presentation.navigation.SearchScreen
import com.example.swapiapp.presentation.navigation.navigateSingleTopTo

@Composable
fun BottomNavigation(
    currentScreen: AppDestination,
    navController: NavHostController
) {
    Row(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth()) {
        Surface(
            modifier = Modifier.weight(1f)
        ) {
            ScreenNavigationButton(
                icon = Icons.Filled.Search,
                isSelected = currentScreen == SearchScreen,
                onClick = {
                    navController.navigateSingleTopTo(SearchScreen.route)
                }
            )
        }
        Surface(
            modifier = Modifier.weight(1f)
        ) {
            ScreenNavigationButton(
                icon = Icons.Filled.Favorite,
                isSelected = currentScreen == FavoritesScreen,
                onClick = {
                    navController.navigateSingleTopTo(FavoritesScreen.route)
                }
            )
        }
    }
}


@Composable
fun ScreenNavigationButton(
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val textColor = if (isSelected) colors.primary else colors.onSurface.copy(alpha = 0.6f)
    val backgroundColor = if (isSelected) colors.primary.copy(alpha = 0.12f) else colors.surface
    val imageAlpha = if (isSelected) 1f else 0.6f
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Image(
            imageVector = icon,
            contentDescription = "Screen Navigation Button",
            colorFilter = ColorFilter.tint(textColor),
            alpha = imageAlpha,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}