package com.example.letsgo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Task

class TaskListViewModel(
    dao: TaskDao
) : ViewModel() {

    val allTasks: LiveData<List<Task>> = dao.getAllTasks()

}