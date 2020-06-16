package com.example.letsgo.data

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.letsgo.models.Task

@Dao
interface TaskDao : DataAccessObject<Task> {

   @RawQuery(observedEntities = [Task::class])
   override fun getByQuery(query: SimpleSQLiteQuery): List<@JvmSuppressWildcards Task>

   @RawQuery(observedEntities = [Task::class])
   override fun fetchByQuery(query: SimpleSQLiteQuery): @JvmSuppressWildcards Task

   @Query("SELECT * FROM ${Task.taskTable}")
   override fun getAll(): List<@JvmSuppressWildcards Task>

   @Query("SELECT * FROM ${Task.taskTable} WHERE id IN (:taskIDs)")
   override fun getByIDs(taskIDs: IntArray): List<@JvmSuppressWildcards Task>

   @Query("SELECT * FROM ${Task.taskTable} WHERE id = :taskID")
   override fun fetchByID(taskID: Int): @JvmSuppressWildcards Task

   @Insert
   override fun insert(vararg elements: @JvmSuppressWildcards Task)

   @Delete
   override fun delete(vararg element: @JvmSuppressWildcards Task)

   @Update
   override fun update(vararg element: @JvmSuppressWildcards Task)


}