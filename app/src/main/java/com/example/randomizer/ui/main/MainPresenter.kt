package com.example.randomizer.ui.main

import android.util.Log
import androidx.lifecycle.Observer
import com.example.randomizer.model.item
import com.example.randomizer.repository.MainRepository

class MainPresenter(override var view: MainContract.View? = null) : MainContract.Presenter {



    override fun onClick(from: Long, to: Long) {
        val value = generate(from, to)
        view?.setValue(value)
        MainRepository.save(item(value))

    }

    override fun onPause() {
        view?.let {view ->
            view.saveValue(view.getValue())
        }
    }


    override fun onResume() {
        view?.let {view ->
            view.setValue(view.loadValue())
        }
    }

    override fun generate(from: Long, to: Long): Long {
        return (from..to).random()
    }

    override fun onDestroy() {
        view = null
    }
}