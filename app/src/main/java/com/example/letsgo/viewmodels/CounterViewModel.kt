package com.example.letsgo.viewmodels

import android.graphics.Point
import androidx.lifecycle.ViewModel
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Counter

class CounterViewModel(
    taskID: Int,
    taskDao: TaskDao
) : ViewModel() {

    val counter: Counter

    init{
        val task = taskDao.fetchByID(taskID)
        counter = Counter(task.value!!)
    }

    fun incrementCounter(){
        counter.increment()
    }

}