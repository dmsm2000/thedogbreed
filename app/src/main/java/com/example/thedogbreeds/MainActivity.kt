package com.example.thedogbreeds

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.thedogbreeds.ui.theme.TheDogBreedsTheme
import com.example.thedogbreeds.viewmodel.DogBreedViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: DogBreedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheDogBreedsTheme {
                Test(viewModel)
            }
        }
    }
}

@Composable
private fun Test(viewModel: DogBreedViewModel) {
    val dogBreeds by viewModel.dogBreeds.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchDogBreeds()
    }

    Column {
        if (dogBreeds.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(dogBreeds) { dogBreed ->
                    Log.d("David Marques", dogBreed.toString())
                }
            }
        }
    }
}
