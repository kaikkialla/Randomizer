package com.example.randomizer.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.icu.util.Measure
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.View.MeasureSpec.AT_MOST
import android.view.View.MeasureSpec.makeMeasureSpec
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.randomizer.Utils
import com.example.randomizer.model.Item
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

class GraphView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    fun setlist(list: ArrayList<Item>) {
        Log.e("fjoasfoa", "set list")
        this.list = list
        invalidate()
    }

    var list: ArrayList<Item>? = null
    private val w = 800
    private val margin = Utils.dpToPx(16, context)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawXaxis(
            canvas,
            margin,
            (height - margin - margin) / 5,
            height - margin
        )
        if(list != null) {
            drawGraph(canvas)
        }
    }


    fun drawGraph(canvas: Canvas) {

        val format = SimpleDateFormat("kk:mm")
        val min = list!!.minBy { it.timestamp }!!.timestamp
        val max = list!!.maxBy { it.timestamp }!!.timestamp

        Log.e("gpjsgjsp", "${format.format(min)}    ${format.format(max)}")

        val time = list!![0].timestamp

        val paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 10f
        val path = Path()

        list!!.forEach {item ->
            canvas.drawPoint( (item.timestamp - time).toFloat(), item.value.toFloat(), paint)
        }


        val datelist = arrayListOf<Timestamp>()

        for(i in 2..4) {
            val time = (max - min) / 5 * i
            datelist.add(Timestamp(min + time))
        }

        datelist.forEach {
            Log.e("gkospgs", "${format.format(it)}")
        }
    }


    fun drawXaxis(
        canvas: Canvas,
        start: Int,
        step: Int,
        end: Int
    ) {
        for(i in start..end step step) {
            canvas.drawLine(
                margin.toFloat(),
                i.toFloat(),
                (canvas.width - margin).toFloat(),
                i.toFloat(),
                Paint())
        }
    }


    fun drawYaxis(
        canvas: Canvas,
        start: Int,
        step: Int,
        end: Int
    ) {
        for(i in start..end step step) {
            canvas.drawLine(i.toFloat(),
                margin.toFloat(),
                i.toFloat(),
                (height - margin).toFloat(),
                Paint())
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val heightMeasureSpec = makeMeasureSpec(w, AT_MOST)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}