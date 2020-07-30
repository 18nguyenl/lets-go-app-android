package com.example.letsgo.views

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import com.example.letsgo.viewmodels.CounterViewModel
import kotlin.random.Random

class CounterCanvas(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private lateinit var viewModel: CounterViewModel

    fun initCanvas(viewModel: CounterViewModel) {
        this.viewModel = viewModel
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(viewModel.counter.bitmap, 0f, 0f, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        viewModel.updateSize(w, h)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

                val x = event.x
                val y = event.y
                val rad = viewModel.counter.getRandomRadius()
                viewModel.counter.placeCircle(x, y, rad)

                // save x and y as part of arraylist so data can persist


                // tell view to update its canvas
                invalidate()

            }
        }

        return super.onTouchEvent(event) // event bubbling / propagation
    }
}