package com.example.thedogbreeds.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.thedogbreeds.model.DogBreed
import com.example.thedogbreeds.view.components.MyCard

@Composable
fun DogBreedDetailScreen(dogBreed: DogBreed, navController: NavHostController) {
    Column {
        Box {
            MyCard(
                imageUrl = dogBreed.image.url,
                title = dogBreed.name,
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
                    )
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
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            TableRow("Weight", dogBreed.weight.metric)
            TableRow("Height", dogBreed.height.metric)
            TableRow("Bred For", dogBreed.bred_for)
            TableRow("Breed Group", dogBreed.breed_group)
            TableRow("Life Span", dogBreed.life_span)
            TableRow("Temperament", dogBreed.temperament)
            TableRow("Origin", dogBreed.origin)
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
        if(value.isNullOrEmpty()) {
            Text(text = "Unknown")
        } else {
            Text(text = value)
        }
    }
}