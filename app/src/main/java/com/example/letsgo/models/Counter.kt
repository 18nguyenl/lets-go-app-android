package com.example.letsgo.models

import android.graphics.Bitmap
import android.graphics.Canvas

class Counter(
    val task: Task
) {

    val intensity = "${task.intensity} ${task.unit}"
    val volume = "${task.sets} × ${task.reps}"
    val hashtag = "#${task.tag}"

    lateinit var canvas: Canvas
    lateinit var bitmap: Bitmap

    private var setProgress = 0

    fun progress() = "$setProgress of ${task.sets}"
    fun isInProgress() = setProgress < task.sets

    fun increment(){

        setProgress++

    }

    fun isBitmapInit(): Boolean {
        return ::bitmap.isInitialized
    }

}