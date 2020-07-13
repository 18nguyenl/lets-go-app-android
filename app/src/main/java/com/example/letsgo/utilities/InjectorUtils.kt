package com.example.letsgo.utilities

import android.content.Context
import com.example.letsgo.data.AppDatabase
import com.example.letsgo.viewmodels.CounterViewModelFactory
import com.example.letsgo.viewmodels.CreateTaskViewModelFactory
import com.example.letsgo.viewmodels.TaskListViewModelFactory

object InjectorUtils {

    private fun getTaskRepository(context: Context) = AppDatabase.getDatabase(context.applicationContext).taskDao()

    // ALL VIEWMODELS REQUIRE APPLICATION CONTEXT

    // counter VM requires the ID of the task being performed
    fun provideCounterViewModelFactory(
        context: Context,
        id: Int
    ) = CounterViewModelFactory(id, getTaskRepository(context))

    // create task VM has no other requirements
    fun provideCreateTaskViewModelFactory(
        context: Context
    ) = CreateTaskViewModelFactory(getTaskRepository(context))

    // task list VM requires array of IDs of tasks being displayed
    fun provideTaskListViewModelFactory(
        context: Context,
        ids: IntArray
    ) = TaskListViewModelFactory(getTaskRepository(context), ids)

    // temporary provider that directly passes all the task IDs to the task list viewmodel
    fun provideAllTasksViewModelFactory(
        context: Context
    ) = TaskListViewModelFactory(getTaskRepository(context), getTaskRepository(context).getAllIDs().value?.toIntArray())

}