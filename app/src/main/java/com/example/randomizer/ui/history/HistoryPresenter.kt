package com.example.randomizer.ui.history

import androidx.lifecycle.Observer
import com.example.randomizer.repository.MainRepository

class HistoryPresenter(
    override var view: HistoryContract.View?
) : HistoryContract.Presenter {
    override fun onResume() {
        view?.let {view ->
            MainRepository.getList().observe(view, Observer { list ->
                view.setText(list.size)
            })
        }

    }

    override fun onPause() {

    }

    override fun onDestroy() {
        view = null
    }


}