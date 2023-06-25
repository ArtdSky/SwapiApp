package com.example.swapiapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.swapiapp.presentation.viewmodel.MainViewModel

/**
 * Функция для управления навигацией между экранами приложения.
 *
 * @param vm ViewModel, используемая для управления состоянием экрана.
 */
@Composable
fun NavState(
    vm: MainViewModel
) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = AppTabRowScreens.find {
        it.route == currentDestination?.route
    } ?: SearchScreen


    NavGraph(
        navController = navController,
        currentScreen = currentScreen,
        vm = vm
    )
}

/**
 * Функция-расширение для [NavHostController], позволяющее выполнить навигацию с флагом singleTop.
 *
 * @param route путь для навигации.
 */
fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}