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
    private val _selectedTask = MutableLiveData<Task>()
    val selectedTask: LiveData<Task>
        get() { return _selectedTask }

    // Make accessing the current Task to be counted "globally" accessible
    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    /**
     * Launching a new coroutine to interact w/ data
     */

    fun getTasks(): LiveData<List<Task>> =  dao.getAll()    // WE DO NOT WANT TO USE THIS, WE NEED TO PHASE IT OUT
    fun fetchById(id: Int): LiveData<Task> = dao.fetchByID(id)
    fun insert(vararg element: Task) = viewModelScope.launch(Dispatchers.IO) { dao.insert(*element) }
    fun delete(vararg element: Task) = viewModelScope.launch(Dispatchers.IO) { dao.delete(*element) }
    fun update(vararg element: Task) = viewModelScope.launch(Dispatchers.IO) { dao.update(*element) }

    fun assignHashtag(taskID: Int, hashtagID: Int){

        fetchById(hashtagID).value!!.addHashtag(taskID)

    }

}