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
        fragment: Fragment,
        ids: IntArray
    ) = TaskListViewModelFactory(getTaskRepository(fragment.requireContext()), ids)

    // temporary provider that directly passes all the task IDs to the task list viewmodel
    // We have to use runBlocking here because all Dao functions must be run in a separate thread or coroutine
    // To make sure a Coroutine executes and ends, we use runBlocking
    fun provideAllTasksViewModelFactoryONE(
        fragment: Fragment
    ) = provideTaskListViewModelFactory(
        fragment,
        runBlocking { getTaskRepository(fragment.requireContext()).getAllIDsSynchronously().toIntArray() }
    )

    // THE FUNCTION BELOW IS MY ATTEMPT AT USING THE LIVEDATA<LIST<INT>> FUNCTION
    // EXCEPT NO IDEA HOW TO USE THE OBSERVER THINGY AS PART OF A CONSTRUCTOR
    // BECAUSE WHERE IS THE RETURN STATEMENT SUPPOSED TO BE???

    // IT COULD BE THAT LIVEDATA SHOULDN'T BE USED LIKE THIS AT ALL,
    // AND THE FUNCTION ABOVE SHOULD WORK ALL ON ITS OWN THE WAY IT'S INTENDED

    //fun provideAllTasksViewModelFactoryTWO(
    //    context: Context
    //) = getTaskRepository(context).getAllIDs().observe(viewLifecycleOwner, Observer { tasks ->
    //    tasks?.let { TaskListViewModelFactory(getTaskRepository(context), it) }
    //})

}