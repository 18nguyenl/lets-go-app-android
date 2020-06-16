package com.example.letsgo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.letsgo.models.Hashtag
import com.example.myfirstapp.models.Hashtag

@Dao
interface HashtagDao : DataAccessObject<Hashtag> {

    @RawQuery(observedEntities = [Hashtag::class])
    override fun getByQuery(query: SimpleSQLiteQuery): LiveData<List<@JvmSuppressWildcards Hashtag>>

    @RawQuery(observedEntities = [Hashtag::class])
    override fun fetchByQuery(query: SimpleSQLiteQuery): LiveData<@JvmSuppressWildcards Hashtag>

    @Query("SELECT * FROM ${Hashtag.hashtagTable}")
    override fun getAll(): LiveData<List<@JvmSuppressWildcards Hashtag>>

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id IN (:ids)")
    override fun getByIDs(ids: IntArray): LiveData<List<@JvmSuppressWildcards Hashtag>>

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id = :id")
    override fun fetchByID(id: Int): LiveData<@JvmSuppressWildcards Hashtag>

    @Insert
    override fun insert(vararg elements: @JvmSuppressWildcards Hashtag)

    @Delete
    override fun delete(vararg element: @JvmSuppressWildcards Hashtag)

    @Update
    override fun update(vararg element: @JvmSuppressWildcards Hashtag)

}