package com.example.thedogbreeds.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.thedogbreeds.model.Destinations
import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.view.components.MyDetailedRow
import com.example.thedogbreeds.viewmodel.DogBreedsViewModel
import com.example.thedogbreeds.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: DogBreedsViewModel, navController: NavHostController) {

    val dogBreeds by viewModel.filteredDogBreeds

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                    .fillMaxWidth()
        ) {
            TextField(
                value = viewModel.searchQuery.value,
                onValueChange = {
                    viewModel.setSearchQuery(it)
                },
                label = {
                    Text(stringResource(id = R.string.search))
                },
                singleLine = true,
            )
            IconButton(onClick = {
                viewModel.toggleOrder()
            }) {
                if (viewModel.order.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_filter_list_off_24),
                        contentDescription = ""
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_filter_list_24),
                        contentDescription = ""
                    )
                }
            }
        }
        if (viewModel.fetchingDogBreeds.value) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {
                CircularProgressIndicator()
            }
        } else {
            ListSection(viewModel = viewModel, dogBreeds = dogBreeds, navController = navController)
        }
    }
}

@Composable
private fun ListSection(viewModel: DogBreedsViewModel, dogBreeds: List<DogBreed>, navController: NavController) {
    LazyColumn {
        itemsIndexed(dogBreeds) { _, dogBreed ->
            MyDetailedRow(
                breedName = dogBreed.name,
                breedGroup = dogBreed.breed_group,
                breedOrigin = dogBreed.origin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clickable {
                        viewModel.setDogBreed(dogBreed)
                        navController.navigate(Destinations.DogBreedDetailsScreen.route)
                    },
            )
            Divider(thickness = 0.5.dp, color = Color.LightGray)
        }
    }
}
