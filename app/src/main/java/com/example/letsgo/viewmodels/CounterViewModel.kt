package com.example.letsgo.viewmodels

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.lifecycle.ViewModel
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Counter
import kotlinx.coroutines.runBlocking

class CounterViewModel(
    taskID: Int,
    taskDao: TaskDao
) : ViewModel() {

    val counter: Counter

    init{
        val task = runBlocking { taskDao.fetchByIDSynchronously(taskID) }
        counter = Counter(task)
    }

    fun incrementCounter(){
        counter.increment()
    }

}