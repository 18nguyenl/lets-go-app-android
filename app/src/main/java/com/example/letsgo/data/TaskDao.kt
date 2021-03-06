package com.example.letsgo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
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

   @Query("SELECT id FROM ${Task.taskTable}")
   fun getAllIDs(): LiveData<List<Int>>

   // Temporarily here for Views
   // Because of the runBlocking thing in InjectorUtils, getAllIDs is only called once and won't update
   // the list of IDs in the future. I think it's possible to switch this into using getAllIDs to fetch the IDs, but
   // we'd need to get rid of the LiveData part of it. There's also some Coroutine concerns I think.
   @Query("SELECT * FROM ${Task.taskTable}")
   fun getAllTasks(): LiveData<List<@JvmSuppressWildcards Task>>

   // Because we need somethings to be accessed directly, and LiveData doesn't have any tooling to fetch data directly,
   // We need to make our own helper methods that lets us do this
   // LiveData.value will always be null at initialization basically so we have to do it this way from what I know
   @Query("SELECT id FROM ${Task.taskTable}")
   suspend fun getAllIDsSynchronously(): List<Int>

   @Query("SELECT * FROM ${Task.taskTable} WHERE id = :taskID")
   suspend fun fetchByIDSynchronously(taskID: Int): Task
}