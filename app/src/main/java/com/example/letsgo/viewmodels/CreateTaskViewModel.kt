package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTaskViewModel(
    private val taskDao: TaskDao
)  : ViewModel() {


    private fun insertTask(vararg element: Task) = viewModelScope.launch(Dispatchers.IO) { taskDao.insert(*element) }

    fun createTask(
        sets: Int,
        reps: Int,
        intensity: Int,
        unit: String
    ) : Task {

        // instantiate task and insert into task database
        val creation = Task(sets, reps, "", intensity, unit)
        insertTask(creation)

        return creation

    }

}