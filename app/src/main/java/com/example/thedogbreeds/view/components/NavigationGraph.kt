package com.example.thedogbreeds.view.components

import androidx.compose.runtime.Composable
import com.example.thedogbreeds.model.Destinations
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.thedogbreeds.view.DogBreedDetailScreen
import com.example.thedogbreeds.view.HomeScreen
import com.example.thedogbreeds.view.SearchScreen
import com.example.thedogbreeds.viewmodel.DogBreedsViewModel

@Composable
fun NavigationGraph(viewModel: DogBreedsViewModel, navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            viewModel.setBottomBarVisible(true)
            HomeScreen(viewModel, navController)
        }
        composable(Destinations.SearchScreen.route) {
            viewModel.setBottomBarVisible(true)
            SearchScreen(viewModel, navController)
        }
        composable(Destinations.DogBreedDetailsScreen.route) {
            viewModel.setBottomBarVisible(false)
            DogBreedDetailScreen(viewModel, navController)
        }
    }
}