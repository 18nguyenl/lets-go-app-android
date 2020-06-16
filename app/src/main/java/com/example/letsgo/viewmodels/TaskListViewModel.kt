package com.example.letsgo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsgo.models.Task
import com.example.letsgo.data.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskListViewModel(
    dao: TaskDao,
    ids: IntArray
) : ViewModel() {

    val tasks: LiveData<List<@JvmSuppressWildcards Task>> = dao.getByIDs(ids)

}