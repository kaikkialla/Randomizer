package com.example.randomizer

import android.os.AsyncTask.execute
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService


object Executor {

    private var mService: ScheduledExecutorService? = null

    fun start() {
        val poolSize = Runtime.getRuntime().availableProcessors() / 2 + 2 // число потоков
        mService = Executors.newScheduledThreadPool(poolSize)
    }

    fun execute(runnable: Runnable) {
        mService!!.execute(runnable)
    }
}