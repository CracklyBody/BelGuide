package com.example.testframe.ui.entertainment.content

import android.content.Context
import android.graphics.PointF
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import java.util.*
import kotlin.math.abs
import kotlin.math.sqrt


class SliderLayoutManager(val context: Context?) : LinearLayoutManager(context) {
    private val MILLISECONDS_PER_INCH = 100f
    private var timer = Timer()
    var currPos: Int = 1
    init {
        orientation = HORIZONTAL
    }

    lateinit var callback: OnItemSelectedListener
    private lateinit var recyclerView: RecyclerView

    override fun onAttachedToWindow(view: RecyclerView) {
        super.onAttachedToWindow(view)
        recyclerView = view

        // Smart snapping
        LinearSnapHelper().attachToRecyclerView(recyclerView)
        recyclerView.isSelected = true
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (currPos >= recyclerView.adapter.let { itemCount }) currPos = 0 else currPos++

                recyclerView.layoutManager?.smoothScrollToPosition(
                    recyclerView,
                    RecyclerView.State(),
                    currPos
                )
            }
        }, 100, 5000)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        scaleDownView()
    }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return if (orientation == HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            scaleDownView()
            scrolled
        } else {
            0
        }
    }

    private fun scaleDownView() {
        val mid = width / 2.0f
        for (i in 0 until childCount) {

            // Расчет расстояния соседа от центра
            val child = getChildAt(i)?:View(context)
            val childMid = (getDecoratedLeft(child) + getDecoratedRight(child)) / 2.0f
            val distanceFromCenter = abs(mid - childMid)

            // Формула маштабирования
            val scale = 1- sqrt((distanceFromCenter / width).toDouble()).toFloat()*0.66f

            // Задаем масштабирование для соседа
            child.scaleX = scale
            child.scaleY = scale
        }
    }
    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)

        // Когда прокрутка останавливается, мы уведомляем выбранный элемент
        if (state == RecyclerView.SCROLL_STATE_IDLE) {

            // Найдем ближайший соседний элемента к центру recyclerView -> это выбранный элемент.
            val recyclerViewCenterX = getRecyclerViewCenterX()
            var minDistance = recyclerView.width
            var position = 0
            for (i in 0 until recyclerView.childCount) {
                val child = recyclerView.getChildAt(i)
                val childCenterX = getDecoratedLeft(child) + (getDecoratedRight(child) - getDecoratedLeft(
                    child
                )) / 2
                val newDistance = abs(childCenterX - recyclerViewCenterX)
                if (newDistance < minDistance) {
                    minDistance = newDistance
                    position = recyclerView.getChildLayoutPosition(child)
                }
            }

            // сообщаем о выбранном элементе
            callback.onItemSelected(position)
        }
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView?,
        state: RecyclerView.State?, position: Int
    ) {
        val smoothScroller: LinearSmoothScroller = object : LinearSmoothScroller(context) {
            //Контролирует направление вы котором будет двигаться smooth scroll
            override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                return this@SliderLayoutManager
                    .computeScrollVectorForPosition(targetPosition)
            }

            //Возвращает время прокрутки одного пикселя в милисекундах
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
            }
            override fun getHorizontalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }
    private fun getRecyclerViewCenterX() : Int {
        return (recyclerView.right - recyclerView.left)/2 + recyclerView.left
    }

    interface OnItemSelectedListener {
        fun onItemSelected(layoutPosition: Int)
    }
}

