package com.example.myfirstapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.myfirstapp.models.Hashtag

@Dao
interface HashtagDao : DataAccessObject<Hashtag> {

    @RawQuery(observedEntities = [Hashtag::class])
    override fun getByQuery(query: SimpleSQLiteQuery): LiveData<List<Hashtag>>

    @Query("SELECT * FROM ${Hashtag.hashtagTable}")
    override fun getAll(): LiveData<List<@JvmSuppressWildcards Hashtag>>

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id IN (:taskIds)")
    override fun getByIDs(taskIds: IntArray): LiveData<List<@JvmSuppressWildcards Hashtag>>

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id = :id")
    override fun getByID(id: Int): LiveData<@JvmSuppressWildcards Hashtag>

    @Insert
    override fun insert(vararg elements: @JvmSuppressWildcards Hashtag)

    @Delete
    override fun delete(vararg element: @JvmSuppressWildcards Hashtag)

    @Update
    override fun update(vararg element: @JvmSuppressWildcards Hashtag)

}