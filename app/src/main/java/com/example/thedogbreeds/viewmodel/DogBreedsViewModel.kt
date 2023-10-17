package com.example.thedogbreeds.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thedogbreeds.data.DogBreedRepository
import com.example.thedogbreeds.model.DogBreed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val PAGE_SIZE = 10

class DogBreedsViewModel : ViewModel() {
    private val repository = DogBreedRepository()

    val allDogBreeds: MutableState<List<DogBreed>> = mutableStateOf(emptyList())
    val filteredDogBreeds: MutableState<List<DogBreed>> = mutableStateOf(emptyList())
    val dogBreeds: MutableState<List<DogBreed>> = mutableStateOf(emptyList())
    var dogBreed: MutableState<DogBreed?> = mutableStateOf(null)

    var listSection = mutableStateOf(false)

    val bottomBarVisible = mutableStateOf(true)
    var page = mutableStateOf(-1)
    var fetchingDogBreeds = mutableStateOf(false)

    var searchQuery = mutableStateOf("")
    var order = mutableStateOf(false)

    init {
        fetchDogBreeds(true)
        getAllDogBreeds()
    }
    fun fetchDogBreeds(nextPage: Boolean) {
        viewModelScope.launch {
            fetchingDogBreeds.value = true
            try {
                val newPageNumber = if (nextPage) page.value + 1 else page.value - 1
                val tempDogBreeds = repository.getDogBreedsPaged(PAGE_SIZE, newPageNumber)
                if(tempDogBreeds.isNotEmpty()) {
                    dogBreeds.value = tempDogBreeds
                    page.value = newPageNumber
                }
            } catch (e: Exception) {
                Log.d("David Marques", e.message.toString())
            }
            delay(1000)
            fetchingDogBreeds.value = false
        }
    }

    private fun getAllDogBreeds() {
        viewModelScope.launch {
            fetchingDogBreeds.value = true
            try {
                val tempDogBreeds = repository.getDogBreeds()
                if(tempDogBreeds.isNotEmpty()) {
                    allDogBreeds.value = tempDogBreeds
                    filteredDogBreeds.value = allDogBreeds.value
                }
            } catch (e: Exception) {
                Log.d("David Marques", e.message.toString())
            }
            delay(1000)
            fetchingDogBreeds.value = false
        }
    }

    fun setSearchQuery(value: String) {
        this.searchQuery.value = value
        if(this.searchQuery.value.isEmpty()) {
            filteredDogBreeds.value = allDogBreeds.value
        }
        filteredDogBreeds.value = allDogBreeds.value.filter { dogBreed ->
            dogBreed.name.contains(searchQuery.value, ignoreCase = true)
        }
    }

    fun toggleOrder() {
        order.value = !order.value
        if(!order.value) {
            filteredDogBreeds.value = filteredDogBreeds.value.sortedBy { dogBreed -> dogBreed.name }
        } else {
            filteredDogBreeds.value = filteredDogBreeds.value.sortedByDescending { dogBreed -> dogBreed.name }
        }
    }

    fun setDogBreed(dogBreed: DogBreed) {
        this.dogBreed.value = dogBreed
    }

    fun setBottomBarVisible(state: Boolean) {
        bottomBarVisible.value = state
    }
}