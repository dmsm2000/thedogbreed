package com.example.thedogbreeds.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    val bottomBarVisible = mutableStateOf(true)
    var page = mutableStateOf(0)

    fun fetchDogBreeds(limit: Int = 10, page: Int) {
        viewModelScope.launch {
            try {
                val dogBreeds = repository.getDogBreeds(limit, page)
                _dogBreeds.value = dogBreeds
            } catch (e: Exception) {
                Log.d("David Marques", e.message.toString())
            }
        }
    }

    fun getDogBreedById(id: Int): DogBreed? {
        return _dogBreeds.value?.find { it.id == id }
    }

    fun setBottomBarVisible(state: Boolean) {
        bottomBarVisible.value = state
    }
}