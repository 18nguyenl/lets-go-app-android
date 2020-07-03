package com.example.letsgo.utilities.shapes

import android.graphics.Canvas
import android.graphics.Paint

abstract class ShapeDrawer(protected val paintColor: Int, protected val paintColorTransluscent: Int) {

    protected val pointPaint = Paint().apply {
        color = paintColor
        style = Paint.Style.FILL
    }
    abstract fun draw(canvas: Canvas, x: Float, y: Float, sets: Int, setsTotal: Int)

}