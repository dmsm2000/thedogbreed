package com.example.thedogbreeds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
                Scaffold(
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            state = viewModel.bottomBarVisible,
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
