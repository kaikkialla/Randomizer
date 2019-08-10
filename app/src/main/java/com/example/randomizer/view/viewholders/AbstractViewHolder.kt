package com.example.randomizer.view.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T)
}