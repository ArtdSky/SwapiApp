package com.example.swapiapp.presentation.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.swapiapp.presentation.navigation.AppDestination
import com.example.swapiapp.presentation.viewmodel.MainViewModel

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    currentScreen: AppDestination,
    vm: MainViewModel
) {
    Text("favorites screen")
}