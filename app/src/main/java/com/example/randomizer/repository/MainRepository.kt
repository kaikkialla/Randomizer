package com.example.randomizer.repository

import android.content.Context
import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.randomizer.Utils.log
import com.example.randomizer.db.AppDatabase
import com.example.randomizer.db.HistoryDao
import com.example.randomizer.model.Item
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.stream.Collector
import java.util.stream.Collectors


object MainRepository {


    fun getList(): LiveData<ArrayList<Item>> {
        load()
        return list
    }

    lateinit var dao: HistoryDao

    private val list: MutableLiveData<ArrayList<Item>> = MutableLiveData()
    private var queue: MutableLiveData<ArrayList<Item>>? = MutableLiveData()
    private val rx_list: BehaviorSubject<ArrayList<Item>> = BehaviorSubject.create()//переделать

    private var hasChanged: Boolean = false

    var load_disposable: Disposable? = null
    var save_disposable: Disposable? = null
    var rx_list_disposable: Disposable? = null
    var delete_disposable: Disposable? = null
    var delete_by_id_disposable: Disposable? = null

    fun initialize(context: Context) {
        dao = AppDatabase.getInstance(context).historyDao()

        rx_list_disposable = rx_list
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                list.value = it
            }
    }


    fun add(item: Item) {
        when(queue?.value) {
            null -> {
                queue?.value = arrayListOf(item)
            }
            else -> {
                val list = queue?.value!!
                list.add(item)
                this.queue?.value = list
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


    fun save() {
        if(hasChanged) {
            log("queue size: ${queue?.value?.size}")
            save_disposable = Single.fromCallable<Any> {
                queue?.value?.forEach { dao.insert(it) }
                true
            }.subscribeOn(Schedulers.io()).subscribe(
                { success ->
                    queue = null
                    queue = MutableLiveData()
                    save_disposable?.dispose()
                    hasChanged = false
                    log("save   success    $success")
                },
                { error ->
                    save_disposable?.dispose()
                    log("save   $error")
                }
            )
        }
    }


    fun generateHash(): String {
        val chars = "1234567890ABCDEF"
        var hex = ""
        val ids = arrayListOf<String>()

        list.value?.forEach { ids.add(it.id) }
        for(i in 1..16) { hex += chars[(Math.random() * 16).toInt()] }
        while (true) { if(!ids.contains(hex)) { return hex } }
    }


    fun delete(item: Item) {
        delete_disposable = Single.fromCallable<Any> {
            dao.delete(item)
            true
        }.subscribeOn(Schedulers.io()).subscribe(
            { success ->
                delete_disposable?.dispose()
                log("delete item   success    $success")
            },
            { error ->
                delete_disposable?.dispose()
                log("delete item   success   $error")
            }
        )
    }

    fun delete(id: String) {
        delete_by_id_disposable = Single.fromCallable<Any> {
            dao.delete(id)
            true
        }.subscribeOn(Schedulers.io()).subscribe(
            { success ->
                delete_by_id_disposable?.dispose()
                log("delete item by id success    $success")
            },
            { error ->
                delete_by_id_disposable?.dispose()
                log("delete item by id success   $error")
            }
        )
    }
}