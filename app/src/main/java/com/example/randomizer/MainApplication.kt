package com.example.randomizer

import android.app.Application
import com.example.randomizer.repository.MainRepository

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Executor.start()
        MainRepository.initialize(this)
    }
}