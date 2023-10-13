package com.example.thedogbreeds.services

import com.example.thedogbreeds.model.DogBreed
import retrofit2.http.GET

interface DogBreedService {
    @GET("breeds")
    suspend fun getDogBreeds(): List<DogBreed>

}