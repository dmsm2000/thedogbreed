package com.example.thedogbreeds.model

data class DogBreed(
    val weight: Weight,
    val height: Height,
    val id: Int,
    val name: String,
    val bred_for: String,
    val breed_group: String,
    val lifeSpan: String,
    val temperament: String,
    val origin: String,
    val reference_image_id: String,
    val image: DogImage
)







