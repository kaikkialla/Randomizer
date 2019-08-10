package com.example.randomizer.ui.main


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomizer.Executor
import com.example.randomizer.R
import com.example.randomizer.repository.MainRepository
import com.example.randomizer.ui.history.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import kotlin.to


const val KEY = "random_value_key"

class MainFragment : Fragment(), MainContract.View {


    lateinit var presenter: MainContract.Presenter
    var number: Long = 0
    lateinit var sp: SharedPreferences


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View{
        sp = activity!!.getSharedPreferences(KEY, Context.MODE_PRIVATE)
        presenter = MainPresenter(this)
        presenter.view = this
        return LayoutInflater.from(context).inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.generate.setOnClickListener {
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

        button.setOnClickListener {
            activity!!
                .supportFragmentManager
                .beginTransaction()
                .addToBackStack("TAG")
                .replace(R.id.f, HistoryFragment())
                .commit()
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        MainRepository.save()
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
        //intent.putExtra(KEY, value)
        sp.edit().putLong(KEY, value).apply()

    }

    override fun loadValue(): Long {
        //return intent.getLongExtra(KEY, 0)
        return sp.getLong(KEY, 0)
    }

    override fun getValue(): Long {
        return number
    }

}


