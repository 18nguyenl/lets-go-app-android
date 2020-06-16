package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import com.example.letsgo.data.DataAccessObject
import com.example.letsgo.models.Hashtag
import com.example.letsgo.models.Task

class CounterViewModel(
    taskID: Int,
    taskDao: DataAccessObject<Task>,
    hashtagDao: DataAccessObject<Hashtag>
) : ViewModel() {

    val task = taskDao.fetchByID(taskID)
    val hashtag = hashtagDao.fetchByID(task.value!!.hashtagID)

    

}