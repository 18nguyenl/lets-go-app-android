package com.example.letsgo.utilities

import android.content.Context
import com.example.letsgo.models.Task
import com.example.letsgo.data.AppDatabase
import com.example.letsgo.data.DataAccessObject
import com.example.letsgo.viewmodels.CounterViewModelFactory
import com.example.letsgo.viewmodels.CreateTaskViewModelFactory
import com.example.letsgo.viewmodels.TaskListViewModelFactory

object InjectorUtils {

    private fun getTaskRepository(
        context: Context
    ) = AppDatabase.getDatabase(context.applicationContext).taskDao()

    private fun getHashtagRepository(
        context: Context
    ) = AppDatabase.getDatabase(context.applicationContext).hashtagDao()

    fun provideCounterModelFactory(
        context: Context,
        id: Int
    ) = CounterViewModelFactory(id, getTaskRepository(context), getHashtagRepository(context))

    fun provideCreateTaskModelFactory(
        context: Context,
        ids: IntArray
    ) = CreateTaskViewModelFactory(getTaskRepository(context))

    fun provideTaskListViewModelFactory(
        context: Context,
        ids: IntArray
    ) = TaskListViewModelFactory(getTaskRepository(context), ids)

}