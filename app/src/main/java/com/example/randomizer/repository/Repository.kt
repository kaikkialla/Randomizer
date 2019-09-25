package com.example.randomizer.repository

import androidx.lifecycle.LiveData
import com.example.randomizer.model.Item

interface Repository {
    fun add(item: Item)

    fun save()

    fun generateHash(): String

    fun getlist(): LiveData<ArrayList<Item>>
}