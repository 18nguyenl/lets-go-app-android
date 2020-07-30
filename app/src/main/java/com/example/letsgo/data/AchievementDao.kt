package com.example.letsgo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.letsgo.models.Achievement

@Dao
interface AchievementDao : DataAccessObject<Achievement> {

    @Query("SELECT * FROM ${Achievement.tableName} WHERE id = :taskID")
    fun fetchByID(taskID: Int): LiveData<Achievement>

}