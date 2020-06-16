package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letsgo.data.TaskDao

class TaskListViewModelFactory(private val dao: TaskDao, private val ids: IntArray): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TaskListViewModel(dao, ids) as T

}