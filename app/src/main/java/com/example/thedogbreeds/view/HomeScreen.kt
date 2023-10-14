package com.example.thedogbreeds.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thedogbreeds.R
import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.view.components.MyCard
import com.example.thedogbreeds.viewmodel.DogBreedViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: DogBreedViewModel) {
    val dogBreeds: List<DogBreed> by viewModel.dogBreeds.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchDogBreeds()
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(dogBreeds.size) { index ->
            MyCard(
                painter = painterResource(id = R.drawable.dogbreed),
                title = dogBreeds[index].name,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
        }
    }
}