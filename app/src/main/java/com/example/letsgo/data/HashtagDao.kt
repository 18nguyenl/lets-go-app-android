package com.example.letsgo.data

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.letsgo.models.Hashtag

@Dao
interface HashtagDao : DataAccessObject<Hashtag> {

    @RawQuery(observedEntities = [Hashtag::class])
    override fun getByQuery(query: SimpleSQLiteQuery): List<@JvmSuppressWildcards Hashtag>

    @RawQuery(observedEntities = [Hashtag::class])
    override fun fetchByQuery(query: SimpleSQLiteQuery): @JvmSuppressWildcards Hashtag

    @Query("SELECT * FROM ${Hashtag.hashtagTable}")
    override fun getAll(): List<@JvmSuppressWildcards Hashtag>

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id IN (:hashtagIDs)")
    override fun getByIDs(hashtagIDs: IntArray): List<@JvmSuppressWildcards Hashtag>

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id = :hashtagID")
    override fun fetchByID(hashtagID: Int): @JvmSuppressWildcards Hashtag

    @Insert
    override fun insert(vararg elements: @JvmSuppressWildcards Hashtag)

    @Delete
    override fun delete(vararg element: @JvmSuppressWildcards Hashtag)

    @Update
    override fun update(vararg element: @JvmSuppressWildcards Hashtag)

}