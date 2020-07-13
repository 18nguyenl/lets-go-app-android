package com.example.letsgo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Task

class TaskListViewModel(
    dao: TaskDao,
    ids: IntArray
) : ViewModel() {

    // This fetches the ids only once. If there are any new IDs, this list won't be updated
    val tasks: LiveData<List<@JvmSuppressWildcards Task>> = dao.getByIDs(ids)

    val allTasks: LiveData<List<Task>> = dao.getAllTasks()
}