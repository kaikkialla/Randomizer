package com.example.randomizer.ui.main

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomizer.Executor
import com.example.randomizer.R
import com.example.randomizer.repository.MainRepository
import kotlinx.android.synthetic.main.activity_main.*


const val KEY = "random_value_key"

class MainActivity : AppCompatActivity(), MainContract.View {


    lateinit var presenter: MainContract.Presenter
    var number: Long = 0
    lateinit var sp: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Executor.EXECUTOR.start()
        MainRepository.initialize(this)


        sp = getSharedPreferences(KEY, Context.MODE_PRIVATE)
        presenter = MainPresenter(this)

        presenter.view = this

        generate.setOnClickListener {
            val from = from
                .text
                .toString()
                .toLong()
            val to = to
                .text
                .toString()
                .toLong()


            presenter.onClick(from, to)
        }
    }






    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }









    override fun setValue(value: Long) {
        number = value
        textview.text = "$value"
    }

    override fun saveValue(value: Long) {
        intent.putExtra(KEY, value)
        //sp.edit().putLong(KEY, value).apply()

    }

    override fun loadValue(): Long {
        return intent.getLongExtra(KEY, 0)
        //return sp.getLong(KEY, 0)
    }

    override fun getValue(): Long {
        return number
    }

}


