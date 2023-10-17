package com.example.thedogbreeds.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thedogbreeds.R
import com.example.thedogbreeds.model.DogBreedEntity
import com.example.thedogbreeds.view.components.InfoRow
import com.example.thedogbreeds.viewmodel.DogBreedsViewModel

@Composable
fun DogBreedOfflineScreen(viewModel: DogBreedsViewModel) {

    val dogBreeds by viewModel.savedDogBreeds

    Column(
        modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.your_favourites),
            modifier = Modifier
                .fillMaxWidth().padding(top = 20.dp, bottom = 20.dp),
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
        )
        LazyColumn {
            itemsIndexed(dogBreeds) { _, dogBreed ->
                MyOfflineDetailedRow(
                    dogBreed = dogBreed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, bottom = 15.dp)
                )
                Divider(thickness = 0.75.dp, color = Color.LightGray)
            }
        }
    }

}

@Composable
fun MyOfflineDetailedRow(dogBreed: DogBreedEntity, modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            InfoRow(stringResource(id = R.string.name), dogBreed.name)
            InfoRow(stringResource(id = R.string.breed_group), dogBreed.breed_group)
            InfoRow(stringResource(id = R.string.breed_for), dogBreed.bred_for)
            InfoRow(stringResource(id = R.string.temperament), dogBreed.temperament)
            InfoRow(stringResource(id = R.string.origin), dogBreed.origin)
            InfoRow(stringResource(id = R.string.life_span), dogBreed.life_span)
        }
    }
}