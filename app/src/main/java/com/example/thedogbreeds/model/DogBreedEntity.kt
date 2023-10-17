package com.example.thedogbreeds.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogBreedEntity(
    @PrimaryKey val id: Int,
    val weight: String?,
    val height: String?,
    val name: String?,
    val bred_for: String?,
    val breed_group: String?,
    val life_span: String?,
    val temperament: String?,
    val origin: String?
)