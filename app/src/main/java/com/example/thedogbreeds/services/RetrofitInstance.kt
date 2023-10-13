package com.example.thedogbreeds.services

import com.example.thedogbreeds.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val dogBreedService: DogBreedService by lazy {
        retrofit.create(DogBreedService::class.java)
    }
}