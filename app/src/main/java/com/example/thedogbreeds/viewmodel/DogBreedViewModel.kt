package com.example.thedogbreeds.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    fun fetchDogBreeds() {
        viewModelScope.launch {
            try {
                val dogBreeds = repository.getDogBreeds()
                _dogBreeds.value = dogBreeds
            } catch (e: Exception) {
                Log.d("David Marques", e.message.toString())
            }
        }
    }
}