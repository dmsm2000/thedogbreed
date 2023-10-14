package com.example.thedogbreeds.view.components

import androidx.compose.runtime.Composable
import com.example.thedogbreeds.model.Destinations
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.view.DogBreedDetailScreen
import com.example.thedogbreeds.view.HomeScreen
import com.example.thedogbreeds.view.SearchScreen
import com.example.thedogbreeds.viewmodel.DogBreedViewModel

@Composable
fun NavigationGraph(viewModel: DogBreedViewModel, navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.HomeScreen.route) {
        composable(Destinations.HomeScreen.route) {
            HomeScreen(viewModel, navController)
        }
        composable(Destinations.SearchScreen.route) {
            SearchScreen(viewModel, navController)
        }
        composable(
            "detail/{dogBreedId}",
            arguments = listOf(navArgument("dogBreedId") { type = NavType.StringType })
        ) { backStackEntry ->
            val dogBreedId = backStackEntry.arguments?.getString("dogBreedId")
            val dogBreed: DogBreed? = viewModel.dogBreeds.value?.find { it.id.toString() == dogBreedId }
            DogBreedDetailScreen(dogBreed, navController)
        }
    }
}