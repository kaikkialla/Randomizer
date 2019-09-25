package com.example.randomizer

import android.content.Context
import com.example.randomizer.db.AppDatabase
import com.example.randomizer.repository.Repository
import com.example.randomizer.repository.RepositoryImpl

fun getRepository(context: Context): Repository =
    RepositoryImpl(
        AppDatabase.getInstance(context).historyDao()
    )