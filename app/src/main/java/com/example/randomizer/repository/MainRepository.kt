package com.example.randomizer.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.room.Room
import com.example.randomizer.db.MainDao
import com.example.randomizer.db.MainDatabase
import com.example.randomizer.model.Item
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject


object MainRepository {

    fun getList(): LiveData<ArrayList<Item>> {
        load()
        return list
    }

    lateinit var dao: MainDao
    private val list: MutableLiveData<ArrayList<Item>> = MutableLiveData()
    private val bslist: BehaviorSubject<ArrayList<Item>> = BehaviorSubject.create()

    fun initialize(context: Context) {
        dao = Room.databaseBuilder(context.applicationContext, MainDatabase::class.java,"database").build().mainDao()

        bslist.observeOn(AndroidSchedulers.mainThread()).subscribe {
            list.value = it
        }
    }



    fun add(item: Item) {
        when(list.value) {
            null -> {
                list.value = arrayListOf(item)
            }
            else -> {
                val list = list.value!!
                list.add(item)
                this.list.value = list

            }
        }
    }


    @SuppressLint("CheckResult")
    fun load() {
        Single.fromCallable<Any> {
            bslist.onNext(dao.all as ArrayList<Item>)
        }.subscribeOn(Schedulers.io()).subscribe(
            {s -> Log.e("TAG", "load   success    $s") },
            {e -> Log.e("TAG", "load   $e")}
        )
    }

    @SuppressLint("CheckResult")
    fun save() {
        Single.fromCallable<Any> {
            dao.deleteAll()
            dao.insert(list.value!!)
            true
        }.subscribeOn(Schedulers.io()).subscribe({s -> Log.e("TAG", "save   success    $s")}, {e -> Log.e("TAG", "save   $e")})
    }
}