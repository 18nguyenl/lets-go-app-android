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
    @ColorInt
    private val setsPaintColor:Int = Color.rgb( 223, 34, 34)
    private val setsPaintColorTransparent = Color.argb(0, 223, 34, 34)
    private val minSetsRadius = 200f
    private val maxSetsRadius = 500f

    private lateinit var viewModel: CounterViewModel

    private val pointPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = setsPaintColor
        style = Paint.Style.FILL
    }

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

                x = event.x
                y = event.y
                tapCanvas(x, y)

                // save x and y as part of arraylist so data can persist

            }
        }

        return super.onTouchEvent(event) // event bubbling / propagation
    }

    private fun tapCanvas(x: Float, y: Float){

        val radius: Float = Random.nextInt(minSetsRadius.toInt(), maxSetsRadius.toInt()).toFloat()
        pointPaint.shader = RadialGradient(x, y, radius, setsPaintColor, setsPaintColorTransparent, Shader.TileMode.CLAMP)
        viewModel.counter.canvas.drawCircle(x, y, radius, pointPaint)
        println("x: $x, y: $y")
        invalidate()

    }
}