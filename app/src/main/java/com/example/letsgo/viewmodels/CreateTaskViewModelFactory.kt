package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letsgo.data.TaskDao

class CreateTaskViewModelFactory(private val dao: TaskDao): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CreateTaskViewModel(dao) as T

}