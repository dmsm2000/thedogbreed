package com.example.thedogbreeds.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.thedogbreeds.R
import com.example.thedogbreeds.model.Destinations
import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.view.components.MyCard
import com.example.thedogbreeds.view.components.MyRow
import com.example.thedogbreeds.viewmodel.DogBreedsViewModel

@Composable
fun HomeScreen(viewModel: DogBreedsViewModel, navController: NavHostController) {

    val dogBreeds: List<DogBreed> by viewModel.dogBreeds

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.home_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 10.dp, start = 20.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 25.sp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 20.dp, end = 10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.dashboard_24),
                    contentDescription = ""
                )
                Switch(
                    checked = viewModel.listSection.value,
                    modifier = Modifier.padding(start = 6.dp, end = 6.dp),
                    onCheckedChange = {
                        viewModel.listSection.value = it
                    }
                )
                Icon(imageVector = Icons.Outlined.List, contentDescription = "")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                if (viewModel.page.value - 1 >= 0) {
                    viewModel.fetchDogBreeds(false)
                }
            }) {
                Icon(Icons.Outlined.ArrowBack, "")
            }
            IconButton(onClick = {
                viewModel.fetchDogBreeds(true)
            }) {
                Icon(Icons.Outlined.ArrowForward, "")
            }
        }
        if (viewModel.showErrorDialog.value) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {
                AlertDialog(
                    title = { Text(text = stringResource(id = R.string.dialog_title)) },
                    text = { Text(text = stringResource(id = R.string.dialog_text)) },
                    onDismissRequest = {
                    viewModel.showErrorDialog.value = false
                },
                    confirmButton = {
                        Button(onClick = {
                            viewModel.showErrorDialog.value = false
                            navController.navigate(Destinations.OfflineScreen.route)
                        }) {
                            Text(text = stringResource(id = R.string.ok))
                        }
                    })
            }
        } else if (viewModel.fetchingDogBreeds.value) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight()
            ) {
                CircularProgressIndicator()
            }
        } else if (!viewModel.listSection.value) {
            CardsSection(
                viewModel = viewModel,
                dogBreeds = dogBreeds,
                navController = navController
            )
        } else {
            ListSection(viewModel = viewModel, dogBreeds = dogBreeds, navController = navController)
        }
    }

}

@Composable
private fun CardsSection(
    viewModel: DogBreedsViewModel,
    dogBreeds: List<DogBreed>,
    navController: NavController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        content = {
            itemsIndexed(dogBreeds) { _, dogBreed ->
                MyCard(
                    imageUrl = dogBreed.image.url,
                    title = dogBreed.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.setDogBreed(dogBreed)
                            navController.navigate(Destinations.DogBreedDetailsScreen.route)
                        },
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    )
}

@Composable
private fun ListSection(
    viewModel: DogBreedsViewModel,
    dogBreeds: List<DogBreed>,
    navController: NavController
) {
    LazyColumn {
        itemsIndexed(dogBreeds) { _, dogBreed ->
            MyRow(
                imageUrl = dogBreed.image.url,
                title = dogBreed.name,
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