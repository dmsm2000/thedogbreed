package com.example.thedogbreeds.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.thedogbreeds.data.DogBreedRepository
import com.example.thedogbreeds.data.getDatabase
import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.model.DogBreedEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val PAGE_SIZE = 10

class DogBreedsViewModel(private val application: Application) : ViewModel() {

    private val repository = DogBreedRepository(getDatabase(application))

    private val allDogBreeds: MutableState<List<DogBreed>> = mutableStateOf(emptyList())
    val filteredDogBreeds: MutableState<List<DogBreed>> = mutableStateOf(emptyList())
    val dogBreeds: MutableState<List<DogBreed>> = mutableStateOf(emptyList())
    var dogBreed: MutableState<DogBreed?> = mutableStateOf(null)
    var savedDogBreeds: MutableState<List<DogBreedEntity>> = mutableStateOf(emptyList())

    var listSection = mutableStateOf(false)

    val bottomBarVisible = mutableStateOf(true)
    var page = mutableStateOf(-1)
    var fetchingDogBreeds = mutableStateOf(false)

    var searchQuery = mutableStateOf("")
    var order = mutableStateOf(false)

    var showErrorDialog = mutableStateOf(false)

    init {
        fetchDogBreeds(true)
        getAllDogBreeds()
        fetchSavedDogBreeds()
    }

    private fun fetchSavedDogBreeds() {
        viewModelScope.launch {
            fetchingDogBreeds.value = true
            try {
                Log.d("David Marques", "ESTIVE AQUI")
                // Perform database operation in the background using Dispatchers.IO
                val savedBreeds = withContext(Dispatchers.IO) {
                    getDatabase(application).dogBreedDao().getAll()
                }
                // Update the LiveData with the result
                savedDogBreeds.value = savedBreeds
            } catch (e: Exception) {
                Log.d("David Marques", e.message.toString())
            }
            delay(1000)
            fetchingDogBreeds.value = false
        }
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
                showErrorDialog.value = true
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
                showErrorDialog.value = true
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

    fun saveDogBreed(dogBreed: DogBreed) {
        repository.insertDogBreed(DogBreedEntity(
            dogBreed.id,
            dogBreed.weight.metric,
            dogBreed.height.metric,
            dogBreed.name,
            dogBreed.bred_for,
            dogBreed.breed_group,
            dogBreed.life_span,
            dogBreed.temperament,
            dogBreed.origin
        ))
    }
}

class DogBreedsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogBreedsViewModel::class.java)) {
            return DogBreedsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}