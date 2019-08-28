package com.example.randomizer.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.example.randomizer.Utils.log
import com.example.randomizer.db.AppDatabase
import com.example.randomizer.db.HistoryDao
import com.example.randomizer.model.Item
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import kotlin.collections.ArrayList


object MainRepository {


    fun getList(): LiveData<ArrayList<Item>> {
        load()
        return list
    }

    lateinit var dao: HistoryDao

    private val list: MutableLiveData<ArrayList<Item>> = MutableLiveData()
    private val queue: MutableLiveData<ArrayList<Item>> = MutableLiveData()
    private val rx_list: BehaviorSubject<ArrayList<Item>> = BehaviorSubject.create()//переделать

    private var hasChanged: Boolean = false

    var load_disposable: Disposable? = null
    var save_disposable: Disposable? = null
    var rx_list_disposable: Disposable? = null


    fun initialize(context: Context) {
        dao = AppDatabase.getInstance(context).historyDao()

        rx_list_disposable = rx_list
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                list.value = it
            }
    }


    fun add(item: Item) {
        when(queue.value) {
            null -> {
                queue.value = arrayListOf(item)
            }
            else -> {
                val list = queue.value!!
                list.add(item)
                this.queue.value = list
            }
        }
        hasChanged = true
    }


    private fun load() {
        load_disposable = Single.fromCallable<Any> {
            rx_list.onNext(dao.all as ArrayList)
        }.subscribeOn(Schedulers.io()).subscribe(
            { success ->
                load_disposable?.dispose()
                log("load   success   $success")
            },
            { error ->
                load_disposable?.dispose()
                log("load  failed    $error")
            }
        )
    }


    @SuppressLint("CheckResult")
    fun save() {
        if(hasChanged) {
            save_disposable = Single.fromCallable<Any> {
                queue.value?.forEach { dao.insert(it) }
                true
            }.subscribeOn(Schedulers.io()).subscribe(
                { success ->
                    save_disposable?.dispose()
                    log("save   success    $success")
                },
                { error ->
                    save_disposable?.dispose()
                    log("save   $error")
                }
            )
        }
    }
}