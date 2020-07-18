package com.example.letsgo.utilities

import android.content.Context
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
    // We have to use runBlocking here because all Dao functions must be run in a separate thread or coroutine
    // To make sure a Coroutine executes and ends, we use runBlocking
    fun provideAllTasksViewModelFactoryONE(
        context: Context
    ) = TaskListViewModelFactory(getTaskRepository(context), runBlocking { getTaskRepository(context).getAllIDsSynchronously().toIntArray() })

    // THE FUNCTION BELOW IS MY ATTEMPT AT USING THE LIVEDATA<LIST<INT>> FUNCTION
    // EXCEPT NO IDEA HOW TO USE THE OBSERVER THINGY AS PART OF A CONSTRUCTOR
    // BECAUSE WHERE IS THE RETURN STATEMENT SUPPOSED TO BE???

    //fun provideAllTasksViewModelFactoryTWO(
    //    context: Context
    //) = getTaskRepository(context).getAllIDs().observe(viewLifecycleOwner, Observer { tasks ->
    //    tasks?.let { TaskListViewModelFactory(getTaskRepository(context), it) }
    //})

}