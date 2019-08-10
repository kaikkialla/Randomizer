package com.example.randomizer.ui.main

import android.util.Log
import androidx.lifecycle.Observer
import com.example.randomizer.model.item
import com.example.randomizer.repository.MainRepository

class MainPresenter(override var view: MainContract.View? = null) : MainContract.Presenter {

    var list = arrayListOf<item>()


    override fun onClick(from: Long, to: Long) {
        view?.setValue(generate(from, to))
        list.add(
        item(
            generate(from, to)
        ))
        MainRepository.add(list)
    }


    override fun onPause() {
        MainRepository.save(list)
        view?.let {view ->
            view.saveValue(view.getValue())
        }
    }



    override fun onResume() {
        view?.let {view ->
            view.setValue(view.loadValue())

            MainRepository
                .getList()
                .observe(view, Observer { list ->
                    this.list = list as ArrayList<item>
                    Log.e("fpsapfa", "${list.size}")
                })
        }
    }

    override fun onDestroy() {
        view = null
    }


    override fun generate(from: Long, to: Long): Long {
        return (from..to).random()
    }
}