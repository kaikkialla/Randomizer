package com.example.randomizer

import android.content.Context
import android.os.AsyncTask
import android.util.DisplayMetrics
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.randomizer.db.HistoryDao
import com.example.randomizer.model.Item
import kotlin.math.roundToInt

object Utils{
    fun dpToPx(dp: Int, context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }


    fun pxToDp(px: Int, context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        return (px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }


    fun log(msg: String) { Log.v("e4b194e9978", msg) }
}