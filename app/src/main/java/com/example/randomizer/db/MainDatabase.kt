package com.example.randomizer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomizer.model.Item


@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}