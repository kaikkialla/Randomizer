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
import com.example.randomizer.model.Item
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers



object MainRepository {

    fun getList(): LiveData<ArrayList<Item>> {
        load()
        return list
    }

    lateinit var dao: MainDao
    private val list: MutableLiveData<ArrayList<Item>> = MutableLiveData()


    fun initialize(context: Context) {
        dao = Room.databaseBuilder(context.applicationContext, MainDatabase::class.java,"database").build().mainDao()
        load()
    }

    private fun load() {
        Executor.EXECUTOR.execute( Runnable {
            list.value = dao.all as ArrayList<Item>
        })
    }

    fun add(item: Item) {
        when(list.value) {
            null -> {
                list.value = arrayListOf(item)
            }
            else -> {
                val list = list.value
                list?.add(item)
                this.list.value = list

            }
        }
    }


    fun save() {
        Single.fromCallable<Any> {
            dao.deleteAll()
            dao.insert(list.value!!)
            true
        }.subscribeOn(Schedulers.io()).subscribe({ _ -> }, { e -> Log.e("gfauogpa", "", e) })

    }
}