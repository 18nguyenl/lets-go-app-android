package com.example.letsgo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsgo.models.Task
import com.example.letsgo.data.DataAccessObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val dao: DataAccessObject<Task>) : ViewModel() {

    var selectedTaskID: Int = 0

    fun getTasks(): List<Task> =  dao.getAll()    // WE DO NOT WANT TO USE THIS, WE NEED TO PHASE IT OUT
    fun getTasks(ids: IntArray) : List<@JvmSuppressWildcards Task> = dao.getByIDs(ids)
    fun fetchTaskById(id: Int) : @JvmSuppressWildcards Task = dao.fetchByID(id)

    private fun insert(vararg element: Task) = viewModelScope.launch(Dispatchers.IO) { dao.insert(*element) }
    private fun delete(vararg element: Task) = viewModelScope.launch(Dispatchers.IO) { dao.delete(*element) }
    private fun update(vararg element: Task) = viewModelScope.launch(Dispatchers.IO) { dao.update(*element) }

    fun createTask(sets: Int, reps: Int, intensity: Int, unit: String) : Task {

        val creation = Task(sets, reps, "", intensity, unit, 0)
        insert(creation)
        return creation

    }

    fun deleteTask(id: Int) {

        delete(fetchTaskById(id))

    }

    fun assignHashtag(taskID: Int, hashtagID: Int) {

        val task = fetchTaskById(taskID)
        task.setHashtag(hashtagID)
        update(task)

    }

}