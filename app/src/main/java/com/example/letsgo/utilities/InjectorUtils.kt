package com.example.letsgo.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.letsgo.data.AppDatabase
import com.example.letsgo.viewmodels.CounterViewModelFactory
import com.example.letsgo.viewmodels.CreateTaskViewModelFactory
import com.example.letsgo.viewmodels.TaskListViewModelFactory
import kotlinx.coroutines.runBlocking
import java.util.Observer

object InjectorUtils {

    private fun getTaskRepository(context: Context) = AppDatabase.getDatabase(context.applicationContext).taskDao()

    // ALL VIEWMODELS REQUIRE APPLICATION CONTEXT

    // counter VM requires the ID of the task being performed
    fun provideCounterViewModelFactory(
        fragment: Fragment,
        id: Int
    ) = CounterViewModelFactory(id, getTaskRepository(fragment.requireContext()))

    // create task VM has no other requirements
    fun provideCreateTaskViewModelFactory(
        fragment: Fragment
    ) = CreateTaskViewModelFactory(getTaskRepository(fragment.requireContext()))

    // task list VM requires array of IDs of tasks being displayed
    fun provideTaskListViewModelFactory(
        fragment: Fragment
    ) = TaskListViewModelFactory(getTaskRepository(fragment.requireContext()))

}