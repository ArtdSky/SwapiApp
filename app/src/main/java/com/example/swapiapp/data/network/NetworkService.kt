package com.example.swapiapp.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkService {
    private const val BASE_URL =
        "https://swapi.dev/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Set the desired log level
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Add the logging interceptor to OkHttpClient
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
//        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


    val swapiService by lazy {
        retrofit.create(NetworkEndpoint::class.java)
    }

    val filmsService by lazy {
        retrofit.create(FilmsEndpoint::class.java)
    }
}