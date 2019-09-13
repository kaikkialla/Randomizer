package com.example.randomizer.ui.history

import androidx.lifecycle.Observer

import com.example.randomizer.repository.Repository

class HistoryPresenter(
    override var view: HistoryContract.View?
) : HistoryContract.Presenter {


    override fun onResume() {
        view?.let {view ->
            Repository.getlist().observe(view, Observer { list ->
                list.sortBy { it.timestamp }
                list.reverse()
                view.swap(list)
            })
        }
    }

    override fun onPause() { }

    override fun onDestroy() {
        view = null
    }
}