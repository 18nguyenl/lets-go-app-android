package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import com.example.letsgo.data.HashtagDao
import com.example.letsgo.data.TaskDao

class CounterViewModel(
    taskID: Int,
    taskDao: TaskDao,
    hashtagDao: HashtagDao
) : ViewModel() {

    val task = taskDao.fetchByID(taskID)
    val hashtag = hashtagDao.fetchByID(task.value!!.hashtagID)



}