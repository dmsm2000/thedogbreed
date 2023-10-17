package com.example.thedogbreeds.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class DogBreed(
    val weight: Weight,
    val height: Height,
    val id: Int,
    val name: String,
    val bred_for: String,
    val breed_group: String,
    val life_span: String,
    val temperament: String,
    val origin: String,
    val reference_image_id: String,
    val image: DogImage
)







