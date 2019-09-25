package com.example.randomizer.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.randomizer.R
import com.example.randomizer.model.Item
import com.example.randomizer.view.viewholders.HistoryViewHolder


class HistoryAdapter(
    private val activity: FragmentActivity
): RecyclerView.Adapter<HistoryViewHolder>() {


    private var items: List<Item> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int) = HistoryViewHolder (
        LayoutInflater.from(viewGroup.context).inflate(R.layout.history_item, viewGroup, false),
        activity
    ).apply {

    }



    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun swap(list: List<Item>) {
        this.items = list
        notifyDataSetChanged()
    }
}
