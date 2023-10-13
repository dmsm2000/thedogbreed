package com.example.thedogbreeds

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    viewModel.fetchDogBreeds()
}