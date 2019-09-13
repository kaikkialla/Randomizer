package com.example.randomizer.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.randomizer.Utils.log
import com.example.randomizer.db.AppDatabase
import com.example.randomizer.db.HistoryDao
import com.example.randomizer.model.Item
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

object Repository {

    fun getlist(): LiveData<ArrayList<Item>> {
        load()
        return list
    }

    lateinit var dao: HistoryDao

    private val list: MutableLiveData<ArrayList<Item>> = MutableLiveData()
    private var queue: MutableLiveData<ArrayList<Item>>? = MutableLiveData()

    private var hasChanged: Boolean = false


    fun initialize(context: Context) {
        dao = AppDatabase.getInstance(context).historyDao()
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
        Load(dao, list).execute()
    }

    fun save() {
        if(hasChanged) {
            Save(dao, queue, hasChanged).execute()
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


    class Load(
        val dao: HistoryDao,
        val list: MutableLiveData<ArrayList<Item>>
    ) : AsyncTask<Void, Void, ArrayList<Item>>() {
        override fun doInBackground(vararg params: Void): ArrayList<Item> {
            return dao.all as ArrayList<Item>
        }

        override fun onPostExecute(list_: ArrayList<Item>) {
            list.value = list_
        }
    }


    class Save(
        val dao: HistoryDao,
        val queue: MutableLiveData<ArrayList<Item>>?,
        var hasChanged: Boolean
    ) : AsyncTask<Void, Void, Void?>() {
        override fun doInBackground(vararg params: Void): Void? {
            queue?.value?.forEach {
                dao.insert(it)
            }
            return null
        }

        override fun onPostExecute(void: Void?) {
            queue?.value?.clear()
            hasChanged = false
        }
    }
}