package com.example.randomizer.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomizer.R
import com.example.randomizer.getRepository
import com.example.randomizer.model.Item
import com.example.randomizer.view.adapters.HistoryAdapter
import kotlinx.android.synthetic.main.history_fragment.*

class HistoryFragment : Fragment(), HistoryContract.View {



    override var presenter: HistoryContract.Presenter? = null

    private val adapter: HistoryAdapter by lazy {
        return@lazy HistoryAdapter(activity!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return LayoutInflater
            .from(context)
            .inflate(
                R.layout.history_fragment,
                container,
                false
            )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HistoryPresenter(this, getRepository(context!!))
        presenter?.view = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun swap(list: List<Item>) {
        adapter.swap(list)
    }
}