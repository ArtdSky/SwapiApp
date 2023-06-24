package com.example.swapiapp.presentation.di

import com.example.swapiapp.data.repository.SwapiRepositoryImpl
import com.example.swapiapp.data.storage.ApiStorage
import com.example.swapiapp.data.storage.Storage
import com.example.swapiapp.domain.repository.SwapiRepository
import com.example.swapiapp.domain.usecase.GetPeopleByName
import com.example.swapiapp.domain.usecase.GetStarshipsByName
import com.example.swapiapp.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val DependencyInjection = module{
    // ViewModel
    viewModel { MainViewModel( get(), get()) }

    // UseCase
    factory { GetPeopleByName(get()) }
    factory { GetStarshipsByName(get()) }

    // Repository
    single<SwapiRepository> { SwapiRepositoryImpl(get()) }

    single<Storage> { ApiStorage() }


}