package com.example.letsgo.data

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.sqlite.db.SimpleSQLiteQuery

interface DataAccessObject<T> {

    @Insert
    fun insert(vararg elements: T)
    @Delete
    fun delete(vararg element: T)
    @Update
    fun update(vararg element: T)

    fun deleteByID(id: Int)

    fun getByQuery(query: SimpleSQLiteQuery): LiveData<List<@JvmSuppressWildcards T>>
    fun fetchByQuery(query: SimpleSQLiteQuery): LiveData<@JvmSuppressWildcards T>

    // FOR TESTING WHILE SINGLE-USER ONLY
    fun getAllIDs(): LiveData<List<Int>>

}