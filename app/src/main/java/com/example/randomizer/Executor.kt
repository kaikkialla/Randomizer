package com.example.randomizer

import android.os.AsyncTask.execute
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


enum class Executor {

    EXECUTOR;

    private var mService: ScheduledExecutorService? = null

    fun start() {
        val poolSize = Runtime.getRuntime().availableProcessors() / 2 + 2 // число потоков
        mService = Executors.newScheduledThreadPool(poolSize)
    }

    fun execute(runnable: Runnable) {
        mService!!.execute(runnable)
    }

    fun execute(runnable: Runnable, timeout: Long) {
        mService!!.schedule(runnable, timeout, TimeUnit.MILLISECONDS)
    }

}