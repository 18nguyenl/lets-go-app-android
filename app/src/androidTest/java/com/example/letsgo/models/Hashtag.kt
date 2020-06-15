package com.example.myfirstapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Hashtag.hashtagTable)
data class Hashtag(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "parentID") val parentID: Int,
    @ColumnInfo(name = "childIDs") val childIDs: HashSet<Int>
) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0

    override fun toString(): String {
        return "#$name"
    }

    companion object{
        val hashtagTable = "hashtags"
    }

}