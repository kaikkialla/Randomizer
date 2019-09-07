package com.example.randomizer.view.viewholders

import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.randomizer.R
import com.example.randomizer.model.Item
import java.text.SimpleDateFormat
import java.util.*

class HistoryViewHolder(
    view: View,
    private val activity: FragmentActivity
): AbstractViewHolder<Item>(view) {

    private val value: TextView = view.findViewById(R.id.value)
    private val timestamp: TextView = view.findViewById(R.id.timestamp)
    private val id: TextView = view.findViewById(R.id.id)

    override fun bind(item: Item) {

        value.text = "${item.value}"

        val string = activity.getString(R.string.date)
        val format1 = SimpleDateFormat("dd/MM", Locale.ROOT)
        val format2 = SimpleDateFormat("kk:mm", Locale.ROOT)
        val date = item.timestamp
        timestamp.text = String.format(string, format1.format(date), format2.format(date))
    }
}