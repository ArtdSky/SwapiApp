package com.example.swapiapp.presentation.navigation


/**
 * Интерфейс, представляющий маршрут навигации приложения.
 */
interface AppDestination {
    val route : String
}

/**
 * Объект, представляющий экран поиска.
 */
object SearchScreen : AppDestination{
    override val route: String
        get() = "search"
}
/**
 * Объект, представляющий экран избранное
 */
object FavoritesScreen : AppDestination{
    override val route: String
        get() = "favorites"
}


/**
 * Список экранов, доступных в нижней навигационной панели.
 */
val AppTabRowScreens = listOf(SearchScreen, FavoritesScreen)