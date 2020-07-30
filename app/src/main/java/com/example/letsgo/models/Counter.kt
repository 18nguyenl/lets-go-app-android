package com.example.letsgo.models

import android.graphics.*
import androidx.annotation.ColorInt
import kotlin.random.Random

class Counter(
    val task: Task
) {

    val intensity = "${task.intensity} ${task.unit}"
    val volume = "${task.sets} × ${task.reps}"
    val hashtag = "#${task.tag}"

    @ColorInt
    private val setsPaintColor:Int = Color.rgb( 223, 34, 34)
    private val setsPaintColorTransparent = Color.argb(0, 223, 34, 34)
    private val minSetsRadius = 200f
    private val maxSetsRadius = 500f

    private val pointPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = setsPaintColor
        style = Paint.Style.FILL
    }

    lateinit var canvas: Canvas
    lateinit var bitmap: Bitmap

    private var setProgress = 0

    fun progress() = "$setProgress of ${task.sets}"
    fun isInProgress() = setProgress < task.sets

    fun getRandomRadius() = Random.nextInt(minSetsRadius.toInt(), maxSetsRadius.toInt()).toFloat()

    fun increment(){

        setProgress++

    }

    fun isBitmapInit(): Boolean {
        return ::bitmap.isInitialized
    }

    fun placeCircle(x: Float, y: Float, rad: Float){

        pointPaint.shader = RadialGradient(x, y, rad, setsPaintColor, setsPaintColorTransparent, Shader.TileMode.CLAMP)
        canvas.drawCircle(x, y, rad, pointPaint)
        println("x: $x, y: $y")

    }

}