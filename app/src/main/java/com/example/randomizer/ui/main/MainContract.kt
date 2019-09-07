package com.example.randomizer.ui.main

import androidx.lifecycle.LifecycleOwner

interface MainContract{
    interface View : LifecycleOwner {

        fun saveValue(value: Long)

        fun loadValue() : Long

        fun setValue(value: Long)

        fun getValue() : Long
    }

    interface Presenter{

        var view: View?

        fun onDestroy()

        fun onResume()

        fun generate(from: Long, to: Long): Long

        fun onPause()

        fun onClick(from: Long, to: Long)
    }
}