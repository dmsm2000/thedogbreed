package com.example.thedogbreeds.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thedogbreeds.R
import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.view.components.MyCard
import com.example.thedogbreeds.view.components.MyRow
import com.example.thedogbreeds.viewmodel.DogBreedViewModel

@Composable
fun HomeScreen(viewModel: DogBreedViewModel) {
    val dogBreeds: List<DogBreed> by viewModel.dogBreeds.observeAsState(emptyList())
    var listSection by remember { mutableStateOf(false) }
    var order by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchDogBreeds()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (dogBreeds.isEmpty()) {
            CircularProgressIndicator()
        } else {
            Text(
                text = "There's a lot of Us!",
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
                        checked = listSection,
                        modifier = Modifier.padding(start = 6.dp, end = 6.dp),
                        onCheckedChange = {
                            listSection = it
                        }
                    )
                    Icon(imageVector = Icons.Outlined.List, contentDescription = "")
                }
                IconButton(onClick = {
                    // TODO: Order the array
                    order = !order
                }, modifier = Modifier.align(Alignment.CenterEnd)) {
                    if (order) {
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
            if (!listSection) {
                CardsSection(dogBreeds = dogBreeds)
            } else {
                ListSection(dogBreeds = dogBreeds)
            }
        }
    }
}

@Composable
fun CardsSection(dogBreeds: List<DogBreed>) {
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
                )
            }
        },
        modifier = Modifier.fillMaxSize().padding(8.dp)
    )
}

@Composable
fun ListSection(dogBreeds: List<DogBreed>) {
    LazyColumn {
        itemsIndexed(dogBreeds) { _, dogBreed ->
            MyRow(
                imageUrl = dogBreed.image.url,
                title = dogBreed.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            )
            Divider(thickness = 0.5.dp, color = Color.LightGray)
        }
    }
}