package com.example.randomizer

import android.app.Application
import com.example.randomizer.repository.Repository

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Repository.initialize(this)
    }
}