package com.example.letsgo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Hashtag.hashtagTable)
data class Hashtag(
    @ColumnInfo(name = "name") val name: String,    // a hashtag's name contains its parent directories, eg. "Parent1/Parent2/ThisTag"
    @ColumnInfo(name = "parentID") val parentID: Int,
    @ColumnInfo(name = "childIDs") val childIDs: HashSet<Int>,
    @ColumnInfo(name = "taskIDs") val taskIDs: HashSet<Int>
) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0

    override fun toString(): String {
        return "#$name"
    }

    fun addChild(childID: Int){
        childIDs.add(childID)
    }

    fun removeChild(childID: Int){
        childIDs.remove(childID)
    }

    fun addTask(taskID: Int){
        taskIDs.add(taskID)
    }

    fun removeTask(taskID: Int){
        taskIDs.remove(taskID)
    }

    companion object{
        const val hashtagTable = "hashtags"
    }

}