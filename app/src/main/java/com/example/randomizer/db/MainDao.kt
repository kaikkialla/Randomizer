package com.example.randomizer.db

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.randomizer.model.item


@Dao
interface MainDao {

    @get:Query("SELECT * FROM `database`")
    val all: List<item>


    @Insert
    fun insert(item: List<item>)

    @Delete
    fun delete(item: ArrayList<item>)

    @Query("DELETE FROM `database`")
    fun deleteAll()




}