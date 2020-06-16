package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letsgo.models.Task
import com.example.letsgo.data.DataAccessObject

class TaskViewModelFactory(private val dao: DataAccessObject<Task>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TaskViewModel(dao) as T

}