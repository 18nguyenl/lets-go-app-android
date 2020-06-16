package com.example.letsgo.utilities

import android.content.Context
import com.example.letsgo.models.Task
import com.example.letsgo.data.AppDatabase
import com.example.letsgo.data.DataAccessObject
import com.example.letsgo.viewmodels.CreateTaskViewModelFactory
import com.example.letsgo.viewmodels.TaskListViewModelFactory

object InjectorUtils {

    private fun getTaskRepository(
        context: Context
    ) = AppDatabase.getDatabase(context.applicationContext).taskDao()

    fun provideTaskListViewModelFactory(
        context: Context,
        ids: IntArray
    ) = TaskListViewModelFactory(getTaskRepository(context), ids)

    fun provideCreateTaskModelFactory(
        context: Context,
        ids: IntArray
    ) = CreateTaskViewModelFactory(getTaskRepository(context))

}