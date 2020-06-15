package com.example.myfirstapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = hashtagTable)
data class Hashtag(
    @ColumnInfo(name = "name") val name: String
) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0

    override fun toString(): String {
        return "#$name"
    }

    companion object{
        val hashtagTable = "hashtags"
    }

}