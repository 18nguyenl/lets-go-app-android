package com.example.letsgo.viewmodels

import android.graphics.Point
import androidx.lifecycle.ViewModel
import com.example.letsgo.data.HashtagDao
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Counter

class CounterViewModel(
    taskID: Int,
    taskDao: TaskDao,
    hashtagDao: HashtagDao
) : ViewModel() {

    val counter: Counter

    init{
        val task = taskDao.fetchByID(taskID)
        val hashtag = hashtagDao.fetchByID(task.value!!.hashtagID)
        counter = Counter(task.value!!, hashtag.value!!.name)
    }

    fun incrementCounter(point: Point){
        counter.increment(point)
    }

}