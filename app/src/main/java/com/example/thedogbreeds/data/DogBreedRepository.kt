package com.example.thedogbreeds.data

import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.model.DogBreedEntity
import com.example.thedogbreeds.services.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogBreedRepository(private val database: DogBreedDatabase) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val dogBreedService = RetrofitInstance.dogBreedService

    suspend fun getDogBreeds(): List<DogBreed> {
        return dogBreedService.getDogBreeds()
    }

    suspend fun getDogBreedsPaged(limit: Int, page: Int): List<DogBreed> {
        return dogBreedService.getDogBreedsPaged(limit, page)
    }

    fun insertDogBreed(dogBreed: DogBreedEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            database.dogBreedDao().insert(dogBreed)
        }
    }
}