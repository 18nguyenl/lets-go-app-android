package com.example.letsgo.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.letsgo.viewmodels.CounterViewModel

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
                println("x: $x, y: $y")
                viewModel.drawCircle(x, y)

                // tell view to update its canvas
                invalidate()

            }
        }

        return super.onTouchEvent(event) // event bubbling / propagation
    }
}