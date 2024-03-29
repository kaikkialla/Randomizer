package com.example.randomizer.ui.main


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.randomizer.R
import com.example.randomizer.getRepository
import com.example.randomizer.ui.history.HistoryFragment
import kotlinx.android.synthetic.main.main_fragment.*


const val KEY = "random_value_key"

class MainFragment : Fragment(), MainContract.View {


    lateinit var presenter: MainContract.Presenter
    var number: Long = 0
    lateinit var sp: SharedPreferences


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View{
        sp = activity!!.getSharedPreferences(KEY, Context.MODE_PRIVATE)
        presenter = MainPresenter(this, getRepository(context!!))
        presenter.view = this
        return LayoutInflater.from(context).inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generate.setOnClickListener {
            if(from.text.isNotEmpty() && to.text.isNotEmpty()) {
                val from = from.text.toString().toLong()
                val to = to.text.toString().toLong()

                if(from < to) {
                    presenter.onClick(from, to)
                }
            }
        }


        history_fragment_button.setOnClickListener {
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
        presenter.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }





    override fun setValue(value: Long) {
        number = value
        output.text = "$value"
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


