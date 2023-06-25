package com.example.swapiapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.swapiapp.presentation.screens.FavoritesScreen
import com.example.swapiapp.presentation.screens.search.SearchScreen
import com.example.swapiapp.presentation.viewmodel.MainViewModel

/**
 * Функция для создания графа навигации.
 *
 * @param navController контроллер навигации.
 * @param currentScreen текущий экран приложения.
 * @param vm ViewModel, используемая для управления состоянием экрана.
 */
@Composable
fun NavGraph(
    navController: NavHostController,
    currentScreen: AppDestination,
    vm: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = SearchScreen.route,

        ) {
        composable(route = SearchScreen.route) {
            SearchScreen(
                navController = navController,
                currentScreen = currentScreen,
                vm = vm
            )
        }

        composable(route = FavoritesScreen.route) {
            FavoritesScreen(
                navController = navController,
                currentScreen = currentScreen,
                vm = vm
            )
        }


    }

}