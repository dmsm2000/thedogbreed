package com.example.thedogbreeds.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String? = null,
    val icon: ImageVector? = null
) {
    object CardsScreen : Destinations(
        route = "cards_screen",
        title = "Cards",
        icon = Icons.Outlined.Home
    )

    object ListScreen : Destinations(
        route = "list_screen",
        title = "List",
        icon = Icons.Outlined.List
    )
}