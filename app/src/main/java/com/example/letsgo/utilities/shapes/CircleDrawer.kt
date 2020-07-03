package com.example.letsgo.utilities.shapes

import android.graphics.*
import kotlin.random.Random

class CircleDrawer(paintColor: Int, paintColorTransluscent: Int, val minRadius: Int, val maxRadius: Int) : ShapeDrawer(paintColor, paintColorTransluscent) {

    fun radius(sets: Int, setsTotal: Int) = Random.nextInt(minRadius, maxRadius).toFloat()

    override fun draw(canvas: Canvas, x: Float, y: Float, sets: Int, setsTotal: Int) {
        val rad = radius(sets, setsTotal)
        pointPaint.shader = RadialGradient(x, y, rad, paintColor, paintColorTransluscent, Shader.TileMode.CLAMP)
        println("x: $x, y: $y")
    }


}