package com.example.letsgo.utilities.shapes

import android.graphics.*
import kotlin.random.Random

class CircleDrawer(color: Int, colorTransluscent: Int, private val minRadius: Int, private val maxRadius: Int) : ShapeDrawer(color, colorTransluscent) {

    private fun radius(sets: Int, setsTotal: Int) = Random.nextInt(minRadius, maxRadius).toFloat()

    override fun draw(canvas: Canvas, x: Float, y: Float, sets: Int, setsTotal: Int) {
        val rad = radius(sets, setsTotal)
        pointPaint.shader = RadialGradient(x, y, rad, color, colorTransluscent, Shader.TileMode.CLAMP)
        canvas.drawCircle(x, y, rad, pointPaint)
        println("x: $x, y: $y")
    }

}