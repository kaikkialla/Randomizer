package com.example.randomizer.ui.history

import androidx.lifecycle.LifecycleOwner

interface HistoryContract{


    interface View : LifecycleOwner {

        fun setText(value: Int)

    }

    interface Presenter {
        var view: View?

        fun onResume()

        fun onPause()

        fun onDestroy()
    }
}