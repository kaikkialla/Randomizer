package com.example.randomizer.ui.main

import android.util.Log
import androidx.lifecycle.Observer
import com.example.randomizer.model.Item
import com.example.randomizer.repository.MainRepository
import java.util.*

class MainPresenter(override var view: MainContract.View? = null) : MainContract.Presenter {



    override fun onClick(from: Long, to: Long) {
        val value = generate(from, to)
        view?.setValue(value)

        MainRepository.add(
            Item(
                value,
                System.currentTimeMillis()
            )
        )
    }

    override fun onPause() {
        Log.e("gaiupgua", "pause")
        MainRepository.save()
        view?.let {view ->
            view.saveValue(view.getValue())
        }
    }


    override fun onResume() {
        Log.e("gaiupgua", "resume")
        view?.let {view ->
            view.setValue(view.loadValue())
            MainRepository.getList().observe(view, Observer {})
        }
    }

    override fun generate(from: Long, to: Long): Long {
        return (from..to).random()
    }

    override fun onDestroy() {
        view = null
    }
}