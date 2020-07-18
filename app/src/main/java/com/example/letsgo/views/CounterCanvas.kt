package com.example.letsgo.views

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import kotlin.random.Random

class CounterCanvas(context: Context, attrs: AttributeSet) : View(context, attrs) {
    @ColorInt
    private val setsPaintColor:Int = Color.rgb( 223, 34, 34)
    private val setsPaintColorTransparent = Color.argb(0, 223, 34, 34)
    private val minSetsRadius = 200f
    private val maxSetsRadius = 500f

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val pointPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = setsPaintColor
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (::extraBitmap.isInitialized) extraBitmap.recycle() // we'll create a new bitmap each time this is called so this will fix the memory leak of extra bitmaps

        extraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x: Float = event.x
        val y: Float = event.y

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

                val radius: Float = Random.nextInt(minSetsRadius.toInt(), maxSetsRadius.toInt()).toFloat()
                pointPaint.shader = RadialGradient(x, y, radius, setsPaintColor, setsPaintColorTransparent, Shader.TileMode.CLAMP)
                extraCanvas.drawCircle(x, y, radius, pointPaint)
                println("x: $x, y: $y")
                invalidate()
            }
        }

        return super.onTouchEvent(event) // event bubbling / propagation
    }
}