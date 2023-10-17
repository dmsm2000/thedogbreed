package com.example.thedogbreeds.data

import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.services.RetrofitInstance

class DogBreedRepository {
    private val dogBreedService = RetrofitInstance.dogBreedService

    suspend fun getDogBreeds(): List<DogBreed> {
        return dogBreedService.getDogBreeds()
    }

    suspend fun getDogBreedsPaged(limit: Int, page: Int): List<DogBreed> {
        return dogBreedService.getDogBreedsPaged(limit, page)
    }
}