package com.example.randomizer.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.randomizer.model.Item


@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        fun getInstance(context: Context) = Room
            .databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "database"
            )
            //.allowMainThreadQueries()
            //.fallbackToDestructiveMigration()
            .build()
    }


    abstract fun historyDao(): HistoryDao
}