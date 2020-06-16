package com.example.letsgo.utilities

import android.content.Context
import com.example.letsgo.models.Task
import com.example.letsgo.data.AppDatabase
import com.example.letsgo.data.DataAccessObject
import com.example.letsgo.viewmodels.TaskViewModelFactory

object InjectorUtils {

    private fun getTaskRepository(context: Context): DataAccessObject<Task> = AppDatabase.getDatabase(context.applicationContext).taskDao()

    fun provideTaskViewModelFactory(context: Context): TaskViewModelFactory = TaskViewModelFactory(getTaskRepository(context))

}