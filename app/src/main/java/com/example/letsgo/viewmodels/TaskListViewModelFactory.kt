package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letsgo.models.Task
import com.example.letsgo.data.DataAccessObject

class TaskListViewModelFactory(private val dao: DataAccessObject<Task>, private val ids: IntArray): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TaskListViewModel(dao, ids) as T

}