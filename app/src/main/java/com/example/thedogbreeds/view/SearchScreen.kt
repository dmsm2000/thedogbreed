package com.example.thedogbreeds.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.view.components.MyDetailedRow
import com.example.thedogbreeds.view.components.MyRow
import com.example.thedogbreeds.viewmodel.DogBreedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: DogBreedViewModel) {
    val dogBreeds by viewModel.dogBreeds.observeAsState(emptyList())
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchDogBreeds()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
            },
            label = {
                Text("Search")
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
        )
        ListSection(dogBreeds = dogBreeds)
    }
}

@Composable
private fun ListSection(dogBreeds: List<DogBreed>) {
    LazyColumn {
        itemsIndexed(dogBreeds) { _, dogBreed ->
            MyDetailedRow(
                breedName = dogBreed.name,
                breedGroup = dogBreed.breed_group,
                breedOrigin = dogBreed.origin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            )
            Divider(thickness = 0.5.dp, color = Color.LightGray)
        }
    }
}
