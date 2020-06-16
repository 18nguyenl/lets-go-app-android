package com.example.letsgo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.letsgo.models.Task

@Dao
interface TaskDao : DataAccessObject<Task> {

   @RawQuery(observedEntities = [Task::class])
   override fun getByQuery(query: SimpleSQLiteQuery): LiveData<List<@JvmSuppressWildcards Task>>

   @Query("SELECT * FROM ${Task.taskTable}")
   override fun getAll(): LiveData<List<@JvmSuppressWildcards Task>>

   @Query("SELECT * FROM ${Task.taskTable} WHERE id IN (:ids)")
   override fun getByIDs(ids: IntArray): LiveData<List<@JvmSuppressWildcards Task>>

   @Query("SELECT * FROM ${Task.taskTable} WHERE id = :id")
   override fun getByID(id: Int): LiveData<@JvmSuppressWildcards Task>

   @Insert
   override fun insert(vararg elements: @JvmSuppressWildcards Task)

   @Delete
   override fun delete(vararg element: @JvmSuppressWildcards Task)

   @Update
   override fun update(vararg element: @JvmSuppressWildcards Task)


}