package com.example.letsgo.views

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import com.example.letsgo.utilities.shapes.CircleDrawer
import com.example.letsgo.utilities.shapes.ShapeDrawer

class CounterCanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    @ColorInt
    private val setsPaintColor:Int = Color.rgb( 223, 34, 34)
    private val setsPaintColorTransparent = Color.argb(0, 223, 34, 34)

    private lateinit var canvas: Canvas
    private lateinit var bitmap: Bitmap
    private val shapeDrawer: ShapeDrawer = CircleDrawer(setsPaintColor, setsPaintColorTransparent, 200, 500)

    private val sets = 5
    private val setsTotal = 5

    private val pointPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = setsPaintColor
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (::bitmap.isInitialized) bitmap.recycle() // we'll create a new bitmap each time this is called so this will fix the memory leak of extra bitmaps

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x: Float = event.x
        val y: Float = event.y

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {

                shapeDrawer.draw(canvas, x, y, sets, setsTotal)
                invalidate()

            }
        }

        return super.onTouchEvent(event) // event bubbling / propagation
    }
}