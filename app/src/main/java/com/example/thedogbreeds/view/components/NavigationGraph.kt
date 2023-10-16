package com.example.thedogbreeds.view.components

import android.app.Activity
import androidx.compose.runtime.Composable
import com.example.thedogbreeds.model.Destinations
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
            viewModel.setBottomBarVisible(true)
            HomeScreen(viewModel, navController)
        }
        composable(Destinations.SearchScreen.route) {
            viewModel.setBottomBarVisible(true)
            SearchScreen(viewModel, navController)
        }
        composable(
            Destinations.DogBreedDetailsScreen.route + "/{dogBreedId}",
            arguments = listOf(navArgument("dogBreedId") { type = NavType.IntType })
        ) { backStackEntry ->
            val dogBreedId = backStackEntry.arguments?.getInt("dogBreedId")
            val dogBreed: DogBreed? = viewModel.getDogBreedById(dogBreedId!!)
            viewModel.setBottomBarVisible(false)
            DogBreedDetailScreen(dogBreed!!, navController)
        }
    }
}