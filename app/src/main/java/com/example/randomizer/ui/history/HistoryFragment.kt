package com.example.randomizer.ui.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomizer.R
import com.example.randomizer.model.Item
import com.example.randomizer.ui.main.KEY
import com.example.randomizer.ui.main.MainPresenter
import com.example.randomizer.view.adapters.HistoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.history_fragment.*

class HistoryFragment : Fragment(), HistoryContract.View {



    lateinit var presenter: HistoryContract.Presenter
    lateinit var adapter: HistoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View{
        return LayoutInflater.from(context).inflate(R.layout.history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = HistoryPresenter(this)
        presenter.view = this

        adapter = HistoryAdapter(activity!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun swap(list: List<Item>) {
        adapter.swap(list)
    }
}