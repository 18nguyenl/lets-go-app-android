package com.example.letsgo.utilities.shapes

import android.graphics.Canvas
import android.graphics.Paint

abstract class ShapeDrawer(protected val color: Int, protected val colorTransluscent: Int) {

    protected val pointPaint = Paint().apply {
        color = color
        style = Paint.Style.FILL
    }
    abstract fun draw(canvas: Canvas, x: Float, y: Float, sets: Int, setsTotal: Int)

}