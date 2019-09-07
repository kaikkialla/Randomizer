package com.example.randomizer

import android.os.AsyncTask.execute
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


enum class Executor {

    EXECUTOR;

    private var mService: ScheduledExecutorService? = null//ExecutorService -> не будет работаеть 2й метод

    fun start() {
        val poolSize = Runtime.getRuntime().availableProcessors() / 2 + 2 // число потоков
        mService = Executors.newScheduledThreadPool(poolSize)
    }

    fun execute(runnable: () -> Unit) {
        mService!!.execute(runnable)
    }

    fun execute(runnable: Runnable) {
        mService!!.execute(runnable)
    }


    fun execute(task: Runnable, delay: Long, unit: TimeUnit) {
        mService!!.schedule(task, delay, unit)
    }
}