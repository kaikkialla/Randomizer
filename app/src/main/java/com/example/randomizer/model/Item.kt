package com.example.randomizer.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp




@Entity(tableName = "db")
data class Item(
    @PrimaryKey @NonNull val value: Long,
    val timestamp: Long,
    val range_start: Long,
    val range_end: Long
)

//@Entity(tableName = "db")
//data class Item(
//    @PrimaryKey @NonNull val value: Long,
//    val timestamp: Long
//)