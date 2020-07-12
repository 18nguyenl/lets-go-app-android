package com.example.letsgo.models

class Counter(
    val task: Task
) {

    val intensity = "${task.intensity} ${task.unit}"
    val volume = "${task.sets}×${task.reps}"

    private var setProgress = 0

    fun progress() = "$setProgress of ${task.sets}"
    fun isInProgress() = setProgress < task.sets

    fun increment(){

        // if counter can still count
        if(isInProgress()){
            // TODO: Add bitmap tracking progress here
        }
    }
}