package com.example.randomizer.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "db")
data class Item(
    @PrimaryKey @NonNull val id: String,
    val value: Long,
    val timestamp: Long,
    val range_start: Long,
    val range_end: Long
)