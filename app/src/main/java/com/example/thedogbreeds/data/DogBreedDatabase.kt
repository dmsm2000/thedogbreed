package com.example.thedogbreeds.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thedogbreeds.model.DogBreedEntity

private lateinit var DATABASE_INSTANCE: DogBreedDatabase

@Database(entities = [DogBreedEntity::class], version = 2)
abstract class DogBreedDatabase : RoomDatabase() {
    abstract fun dogBreedDao(): DogBreedDao
}

fun getDatabase(context: Context): DogBreedDatabase {
    synchronized(DogBreedDatabase::class.java) {
        if (!::DATABASE_INSTANCE.isInitialized) {
            DATABASE_INSTANCE = Room.databaseBuilder(
                context.applicationContext, DogBreedDatabase::class.java, "DogBreeds"
            ).fallbackToDestructiveMigration().build()
        }
        return DATABASE_INSTANCE
    }
}