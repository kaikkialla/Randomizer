package com.example.randomizer.view.viewholders

import android.provider.Settings.Global.getString
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
import java.util.*

class HistoryViewholder(
    val view: View,
    val activity: FragmentActivity
): AbstractViewHolder<Item>(view) {

    val value = view.findViewById<TextView>(R.id.value)
    val timestamp = view.findViewById<TextView>(R.id.timestamp)
    val id = view.findViewById<TextView>(R.id.id)

    override fun bind(item: Item) {

        value.text = "${item.value}"

        val string = activity.getString(R.string.date)
        val format1 = SimpleDateFormat("dd/MM", Locale.ROOT)
        val format2 = SimpleDateFormat("kk:mm", Locale.ROOT)
        val date = item.timestamp
        timestamp.text = String.format(string, format1.format(date), format2.format(date))
    }
}