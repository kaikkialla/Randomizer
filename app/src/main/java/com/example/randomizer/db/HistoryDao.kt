package com.example.randomizer.db

import androidx.room.*
import com.example.randomizer.model.Item


@Dao
interface HistoryDao {

    @get:Query("SELECT * FROM db")
    val all: List<Item>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Item)

    @Delete
    fun delete(item: List<Item>)

    @Query("DELETE FROM db")
    fun deleteAll()
}