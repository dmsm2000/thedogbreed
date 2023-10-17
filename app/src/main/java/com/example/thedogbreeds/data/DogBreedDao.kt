package com.example.thedogbreeds.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.thedogbreeds.model.DogBreedEntity

@Dao
interface DogBreedDao {
    @Query("SELECT * FROM DogBreedEntity")
    fun getAll(): List<DogBreedEntity>

    @Insert(onConflict = REPLACE)
    fun insert(dogBreedEntity: DogBreedEntity)

    @Delete
    fun delete(dogBreedEntity: DogBreedEntity)
}