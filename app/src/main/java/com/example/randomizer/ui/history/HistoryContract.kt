package com.example.randomizer.ui.history

import androidx.lifecycle.LifecycleOwner
import com.example.randomizer.model.Item

interface HistoryContract{


    interface View : LifecycleOwner {
        var presenter: Presenter?

        fun swap(list: List<Item>)
    }

    interface Presenter {
        var view: View?

        fun onResume()

        fun onPause()

        fun onDestroy()
    }
}