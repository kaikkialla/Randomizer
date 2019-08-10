package com.example.randomizer.ui.history

import android.util.Log
import androidx.lifecycle.Observer
import com.example.randomizer.repository.MainRepository

class HistoryPresenter(
    override var view: HistoryContract.View?
) : HistoryContract.Presenter {


    override fun onResume() {
        view?.let {view ->
            MainRepository.getList().observe(view, Observer { list ->
                view.swap(list)
            })
        }
    }

    override fun onPause() {

    }

    override fun onDestroy() {
        view = null
    }


}