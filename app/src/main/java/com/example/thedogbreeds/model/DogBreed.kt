package com.example.thedogbreeds.model

data class DogBreed(
    val weight: Weight,
    val height: Height,
    val id: Int,
    val name: String,
    val bredFor: String,
    val breedGroup: String,
    val lifeSpan: String,
    val temperament: String,
    val origin: String,
    val referenceImageId: String,
    val image: DogImage
)







