package com.example.randomizer.view.adapters

import android.os.AsyncTask
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.randomizer.R
import com.example.randomizer.model.Item
import com.example.randomizer.view.viewholders.HistoryViewholder


class HistoryAdapter: RecyclerView.Adapter<HistoryViewholder>() {


    var items: List<Item> = ArrayList()


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int) = HistoryViewholder (
        LayoutInflater.from(viewGroup.context).inflate(R.layout.history_item, viewGroup, false)
    ).apply {}



    override fun getItemCount(): Int {
        Log.e("gojsgs", items.size.toString())
        return items.size
    }


    override fun onBindViewHolder(holder: HistoryViewholder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    fun swap(list: List<Item>) {
        this.items = list
        notifyDataSetChanged()
    }


}
