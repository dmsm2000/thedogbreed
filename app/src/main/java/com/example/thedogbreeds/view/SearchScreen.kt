package com.example.thedogbreeds.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.thedogbreeds.viewmodel.DogBreedViewModel

@Composable
fun SearchScreen(viewModel: DogBreedViewModel) {
    val dogBreeds by viewModel.dogBreeds.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchDogBreeds()
    }

    Column {
        Text(text = "List Screen")
    }
}