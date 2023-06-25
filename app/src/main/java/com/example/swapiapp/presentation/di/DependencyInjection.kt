package com.example.swapiapp.presentation.di

import com.example.swapiapp.data.local.AppDatabase
import com.example.swapiapp.data.local.AppRepository
import com.example.swapiapp.data.repository.RepositoryImpl
import com.example.swapiapp.data.storage.people.ApiPeopleLocalStorage
import com.example.swapiapp.data.storage.people.ApiPeopleNetworkStorage
import com.example.swapiapp.data.storage.starships.ApiStarshipsLocalStorage
import com.example.swapiapp.data.storage.starships.ApiStarshipsNetworkStorage
import com.example.swapiapp.domain.repository.FilmsRepository
import com.example.swapiapp.domain.repository.PeopleRepository
import com.example.swapiapp.domain.repository.StarshipsRepository
import com.example.swapiapp.domain.usecase.FavoritePeople
import com.example.swapiapp.domain.usecase.FavoriteStarships
import com.example.swapiapp.domain.usecase.FetchFilm
import com.example.swapiapp.domain.usecase.GetPeopleByName
import com.example.swapiapp.domain.usecase.GetStarshipsByName
import com.example.swapiapp.presentation.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DependencyInjection = module {
    // ViewModel
    viewModel { MainViewModel(get(), get(), get(), get(), get()) }

    // UseCase
    factory { GetPeopleByName(get()) }
    factory { GetStarshipsByName(get()) }
    factory { FavoritePeople(get()) }
    factory { FavoriteStarships(get()) }
    factory { FetchFilm(get()) }

    // Repository
    single<PeopleRepository> { RepositoryImpl(get(), get(), get(), get()) }
    single<StarshipsRepository> { RepositoryImpl(get(), get(), get(), get()) }
    single<FilmsRepository> { RepositoryImpl(get(), get(), get(), get()) }

    // Storage
    single<ApiPeopleNetworkStorage> { ApiPeopleNetworkStorage() }
    single<ApiStarshipsNetworkStorage> { ApiStarshipsNetworkStorage() }
    single<ApiPeopleLocalStorage> { ApiPeopleLocalStorage(get()) }
    single<ApiStarshipsLocalStorage> { ApiStarshipsLocalStorage(get()) }

    // Database
    single { AppRepository(get(), get()) }
    single { get<AppDatabase>().starshipsDao() }
    single { get<AppDatabase>().peopleDao() }
    single { AppDatabase.getDatabase(androidContext()) }

}