package com.example.randomizer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomizer.model.item


@Database(entities = [item::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract val mainDao: MainDao
}