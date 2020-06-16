package com.example.letsgo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.letsgo.models.Hashtag

@Dao
interface HashtagDao : DataAccessObject<Hashtag> {

    @RawQuery(observedEntities = [Hashtag::class])
    override fun getByQuery(query: SimpleSQLiteQuery): LiveData<List<@JvmSuppressWildcards Hashtag>>

    @RawQuery(observedEntities = [Hashtag::class])
    override fun fetchByQuery(query: SimpleSQLiteQuery): LiveData<@JvmSuppressWildcards Hashtag>

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id IN (:hashtagIDs)")
    fun getByIDs(hashtagIDs: IntArray): LiveData<List<@JvmSuppressWildcards Hashtag>>

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id = :hashtagID")
    fun fetchByID(hashtagID: Int): LiveData<@JvmSuppressWildcards Hashtag>

    @Insert
    override fun insert(vararg elements: Hashtag)

    @Delete
    override fun delete(vararg element: Hashtag)

    @Query("DELETE FROM ${Hashtag.hashtagTable} WHERE id = :id")
    override fun deleteByID(id: Int)

    @Update
    override fun update(vararg element: Hashtag)

    @Query("SELECT id FROM ${Hashtag.hashtagTable}")
    override fun getAllIDs(): LiveData<List<Int>>

}