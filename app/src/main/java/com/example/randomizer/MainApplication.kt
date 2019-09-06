package com.example.randomizer

import android.app.Application
import android.telecom.Call
import android.util.Log
import com.example.randomizer.repository.MainRepository

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Executor.EXECUTOR.start()
        MainRepository.initialize(this)
    }
}