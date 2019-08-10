package com.example.randomizer.view.viewholders

import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.randomizer.R
import com.example.randomizer.model.Item
import org.w3c.dom.Text
import java.text.SimpleDateFormat

class HistoryViewholder(
    val view: View
): AbstractViewHolder<Item>(view) {

    val value = view.findViewById<TextView>(R.id.value)
    val timestamp = view.findViewById<TextView>(R.id.timestamp)

    override fun bind(item: Item) {

        value.text = "${item.value}"

        val format1 = SimpleDateFormat("dd/MM")
        val format2 = SimpleDateFormat("kk:mm")
        val date = item.timestamp
        timestamp.text = "${format1.format(date)} в ${format2.format(date)}"
    }
}