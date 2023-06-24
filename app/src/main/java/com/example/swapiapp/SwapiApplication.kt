package com.example.swapiapp

import android.app.Application
import com.example.swapiapp.presentation.di.DependencyInjection
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SwapiApplication  : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SwapiApplication)
            modules(listOf( DependencyInjection) )
        }
    }
}