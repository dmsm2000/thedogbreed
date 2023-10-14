package com.example.thedogbreeds

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thedogbreeds.ui.theme.TheDogBreedsTheme
import com.example.thedogbreeds.view.components.BottomBar
import com.example.thedogbreeds.view.components.NavigationGraph
import com.example.thedogbreeds.viewmodel.DogBreedViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: DogBreedViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheDogBreedsTheme {
                val navController: NavHostController = rememberNavController()
                val buttonsVisible = remember { mutableStateOf(true) }

                Scaffold(
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            state = buttonsVisible,
                            modifier = Modifier
                        )
                    }) { paddingValues ->
                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        NavigationGraph(viewModel = viewModel, navController = navController)
                    }
                }
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
