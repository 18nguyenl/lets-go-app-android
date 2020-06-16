package com.example.letsgo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.letsgo.models.Task

@Dao
interface TaskDao : DataAccessObject<Task> {

   @Query("SELECT * FROM ${Task.taskTable} WHERE id IN (:taskIDs)")
   fun getByIDs(taskIDs: IntArray): LiveData<List<@JvmSuppressWildcards Task>>

   @Query("SELECT * FROM ${Task.taskTable} WHERE id = :taskID")
   fun fetchByID(taskID: Int): LiveData<@JvmSuppressWildcards Task>

   //@RawQuery(observedEntities = [Task::class])
   //fun getByQuery(query: SimpleSQLiteQuery): LiveData<List<@JvmSuppressWildcards Task>>

   //@RawQuery(observedEntities = [Task::class])
   //fun fetchByQuery(query: SimpleSQLiteQuery): LiveData<@JvmSuppressWildcards Task>

   //@Query("DELETE FROM ${Task.taskTable} WHERE id = :id")
   //fun deleteByID(id: Int)

   //@Query("SELECT id FROM ${Task.taskTable}")
   //fun getAllIDs(): LiveData<List<Int>>

}