package com.example.thedogbreeds.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object HomeScreen : Destinations(
        route = "home_screen",
        icon = Icons.Outlined.Home
    )

    object SearchScreen : Destinations(
        route = "search_screen",
        icon = Icons.Outlined.Search
    )

    object DogBreedDetailsScreen : Destinations (
        route = "detail"
    )

    object OfflineScreen : Destinations (
        route = "offline"
    )
}