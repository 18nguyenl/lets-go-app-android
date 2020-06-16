package com.example.letsgo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.letsgo.models.Task

@Dao
interface TaskDao : DataAccessObject<Task> {

   @RawQuery(observedEntities = [Task::class])
   override fun getByQuery(query: SimpleSQLiteQuery): LiveData<List<@JvmSuppressWildcards Task>>

   @RawQuery(observedEntities = [Task::class])
   override fun fetchByQuery(query: SimpleSQLiteQuery): LiveData<@JvmSuppressWildcards Task>

   @Query("SELECT * FROM ${Task.taskTable} WHERE id IN (:taskIDs)")
   override fun getByIDs(taskIDs: IntArray): LiveData<List<@JvmSuppressWildcards Task>>

   @Query("SELECT * FROM ${Task.taskTable} WHERE id = :taskID")
   override fun fetchByID(taskID: Int): LiveData<@JvmSuppressWildcards Task>

   @Query("DELETE FROM ${Task.taskTable} WHERE id = :id")
   override fun deleteByID(id: Int)

   @Query("SELECT id FROM ${Task.taskTable}")
   override fun getAllIDs(): LiveData<List<Int>>

}