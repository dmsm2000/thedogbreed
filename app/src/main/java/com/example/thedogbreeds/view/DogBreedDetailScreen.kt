package com.example.thedogbreeds.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.thedogbreeds.view.components.MyCard
import com.example.thedogbreeds.R
import com.example.thedogbreeds.viewmodel.DogBreedsViewModel

@Composable
fun DogBreedDetailScreen(viewModel: DogBreedsViewModel, navController: NavHostController) {

    val dogBreed by viewModel.dogBreed

    Column {
        Box {
            MyCard(
                imageUrl = dogBreed!!.image.url,
                title = dogBreed!!.name,
                modifier = Modifier.fillMaxHeight(0.5f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Black,
                                Color.Transparent
                            ),
                        ),
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                IconButton(
                    onClick = {
                        viewModel.saveDogBreed(dogBreed!!)
                    }) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            TableRow(stringResource(id = R.string.weight), dogBreed!!.weight.metric)
            TableRow(stringResource(id = R.string.height), dogBreed!!.height.metric)
            TableRow(stringResource(id = R.string.breed_for), dogBreed!!.bred_for)
            TableRow(stringResource(id = R.string.breed_group), dogBreed!!.breed_group)
            TableRow(stringResource(id = R.string.life_span), dogBreed!!.life_span)
            TableRow(stringResource(id = R.string.temperament), dogBreed!!.temperament)
            TableRow(stringResource(id = R.string.origin), dogBreed!!.origin)
        }
    }
}

@Composable
fun TableRow(label: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.5f)
        )
        if (value.isNullOrEmpty()) {
            Text(text = stringResource(id = R.string.unknown))
        } else {
            Text(text = value)
        }
    }
}