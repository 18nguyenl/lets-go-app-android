package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letsgo.data.TaskDao

class AchievementViewModelFactory(
    private val taskID: Int,
    private val taskDao: TaskDao
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = AchievementViewModel(taskID, taskDao) as T
}