package com.example.thedogbreeds.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
                        contentDescription = "Back"
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            TableRow("Weight", dogBreed.weight.metric?: "Unknown")
            TableRow("Height", dogBreed.height.metric?: "Unknown")
            TableRow("Bred For", dogBreed.bred_for?: "Unknown")
            TableRow("Breed Group", dogBreed.breed_group?: "Unknown")
            TableRow("Life Span", dogBreed.lifeSpan?: "Unknown")
            TableRow("Temperament", dogBreed.temperament?: "Unknown")
            TableRow("Origin", dogBreed.origin?: "Unknown")
        }
    }
}

@Composable
fun TableRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .width(120.dp)
        )
        Text(text = value)
    }
}