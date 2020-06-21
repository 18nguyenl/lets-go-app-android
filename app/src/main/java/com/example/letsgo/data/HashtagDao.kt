package com.example.letsgo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.letsgo.models.Hashtag

@Dao
interface HashtagDao : DataAccessObject<Hashtag> {

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id = :hashtagID")
    fun fetchByID(hashtagID: Int): LiveData<@JvmSuppressWildcards Hashtag>

    @Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE name = :hashtagName")
    fun fetchByName(hashtagName: String): LiveData<@JvmSuppressWildcards Hashtag>
    // user ID should be added to the above function

    //@RawQuery(observedEntities = [Hashtag::class])
    //fun getByQuery(query: SimpleSQLiteQuery): LiveData<List<@JvmSuppressWildcards Hashtag>>

    @RawQuery(observedEntities = [Hashtag::class])
    fun fetchByQuery(query: SimpleSQLiteQuery): LiveData<@JvmSuppressWildcards Hashtag>

    //@Query("SELECT * FROM ${Hashtag.hashtagTable} WHERE id IN (:hashtagIDs)")
    //fun getByIDs(hashtagIDs: IntArray): LiveData<List<@JvmSuppressWildcards Hashtag>>

    //@Query("DELETE FROM ${Hashtag.hashtagTable} WHERE id = :id")
    //fun deleteByID(id: Int)

    //@Query("SELECT id FROM ${Hashtag.hashtagTable}")
    //fun getAllIDs(): LiveData<List<Int>>

}