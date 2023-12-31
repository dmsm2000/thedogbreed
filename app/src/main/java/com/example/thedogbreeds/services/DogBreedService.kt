package com.example.thedogbreeds.services

import com.example.thedogbreeds.model.DogBreed
import retrofit2.http.GET
import retrofit2.http.Query

interface DogBreedService {
    @GET("breeds")
    suspend fun getDogBreeds(): List<DogBreed>
    @GET("breeds")
    suspend fun getDogBreedsPaged(@Query("limit") limit: Int, @Query("page") page: Int): List<DogBreed>
}