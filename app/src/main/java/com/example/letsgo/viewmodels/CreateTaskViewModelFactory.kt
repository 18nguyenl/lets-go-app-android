package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letsgo.data.DataAccessObject
import com.example.letsgo.models.Task

class CreateTaskViewModelFactory(private val dao: DataAccessObject<Task>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CreateTaskViewModel(dao) as T

}