package com.example.randomizer.repository

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.randomizer.Executor
import com.example.randomizer.db.MainDao
import com.example.randomizer.db.MainDatabase
import com.example.randomizer.model.item
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import android.os.AsyncTask.execute



object MainRepository {



    fun getList(): LiveData<List<item>> {
        load()
        return list
    }

    lateinit var dao: MainDao
    val list: MutableLiveData<List<item>> = MutableLiveData()





    fun initialize(context: Context) {
        dao = Room.databaseBuilder(context.applicationContext, MainDatabase::class.java,"database").build().mainDao
    }

    fun add(list: ArrayList<item>) {
        this.list.value = list
    }


    private fun load() {
        Executor.execute( Runnable {
            list.value = dao.all
        })
    }



    fun save(list: List<item>) {
        Executor.execute(Runnable {
            Single.fromCallable<Any> {
                dao.deleteAll()
                dao.insert(list)
                true
            }.subscribeOn(Schedulers.io()).subscribe({ ignore -> }, { e -> Log.e("TEST", "", e) })
        })
    }
}