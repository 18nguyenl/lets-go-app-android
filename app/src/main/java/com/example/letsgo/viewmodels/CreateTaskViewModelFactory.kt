package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letsgo.data.HashtagDao
import com.example.letsgo.data.TaskDao

class CreateTaskViewModelFactory(
    private val taskDao: TaskDao,
    private val hashtagDao: HashtagDao
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = CreateTaskViewModel(taskDao, hashtagDao) as T

}