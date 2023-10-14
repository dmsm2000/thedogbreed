package com.example.thedogbreeds.view.components

import androidx.compose.runtime.Composable
import com.example.thedogbreeds.model.Destinations
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thedogbreeds.view.HomeScreen
import com.example.thedogbreeds.view.SearchScreen
import com.example.thedogbreeds.viewmodel.DogBreedViewModel

@Composable
fun NavigationGraph(viewModel: DogBreedViewModel, navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            HomeScreen(viewModel)
        }
        composable(Destinations.SearchScreen.route) {
            SearchScreen(viewModel)
        }
    }
}