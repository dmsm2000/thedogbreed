package com.example.thedogbreeds.view.components

import androidx.compose.runtime.Composable
import com.example.thedogbreeds.model.Destinations
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thedogbreeds.view.CardsScreen
import com.example.thedogbreeds.view.ListScreen
import com.example.thedogbreeds.viewmodel.DogBreedViewModel

@Composable
fun NavigationGraph(viewModel: DogBreedViewModel, navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.CardsScreen.route) {
        composable(Destinations.CardsScreen.route) {
            CardsScreen(viewModel)
        }
        composable(Destinations.ListScreen.route) {
            ListScreen(viewModel)
        }
    }
}