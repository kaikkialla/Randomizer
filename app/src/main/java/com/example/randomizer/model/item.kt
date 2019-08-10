package com.example.randomizer.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp




@Entity(tableName = "database")
data class item(

    @PrimaryKey @NonNull
    val value: Long
)