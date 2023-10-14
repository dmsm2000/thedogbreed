package com.example.thedogbreeds.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thedogbreeds.data.DogBreedRepository
import com.example.thedogbreeds.model.DogBreed
import kotlinx.coroutines.launch

class DogBreedViewModel : ViewModel() {
    private val repository = DogBreedRepository()

    private val _dogBreeds = MutableLiveData<List<DogBreed>>()
    val dogBreeds: LiveData<List<DogBreed>> = _dogBreeds

    fun fetchDogBreeds(limit: Int = 10, page: Int = 0) {
        viewModelScope.launch {
            try {
                val dogBreeds = repository.getDogBreeds(limit, page)
                _dogBreeds.value = dogBreeds
            } catch (e: Exception) {
                Log.d("David Marques", e.message.toString())
            }
        }
    }
}