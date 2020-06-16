package com.example.letsgo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.letsgo.data.DataAccessObject
import com.example.letsgo.models.Hashtag
import com.example.letsgo.utilities.HashtagUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HashtagViewModel(private val dao: DataAccessObject<Hashtag>) : ViewModel() {

    fun createHashtag(hashtag: String) : Hashtag{

        // we will not expect to create any child
        val childIDs: HashSet<Int> = HashSet()
        var parentID: Int = 0

        //if this hashtag is a child of another, check if it exists
        val parentName = HashtagUtils.getPath(hashtag)
        if(!parentName.equals("")){

            val parentTag: Hashtag? = dao.getByQuery(SimpleSQLiteQuery("SELECT * FROM ${Hashtag.hashtagTable} WHERE name = $parentName"))

            // if parentTag

        }

        val creation = Hashtag(hashtag, parentID, childIDs)
        dao.insert(Hashtag(hashtag, parentID, childIDs))
        return creation

    }

}