package com.example.letsgo.models

import android.graphics.Point

class Counter(
    val task: Task,
    hashtagName: String
) {

    val points = ArrayList<Point>()
    val intensity = "${task.intensity} ${task.unit}"
    val volume = "${task.sets}×${task.reps}"
    val hashtag = "#$hashtagName"

    private var setProgress = 0

    fun progress() = "$setProgress of ${task.sets}"
    fun isInProgress() = setProgress < task.sets

    fun increment(point: Point){

        // if counter can still count
        if(isInProgress()){

            // add point and increment reps
            points.add(point)
            setProgress

        }

    }

}