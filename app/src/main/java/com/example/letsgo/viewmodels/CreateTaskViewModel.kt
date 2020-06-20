package com.example.letsgo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.letsgo.data.HashtagDao
import com.example.letsgo.models.Task
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Hashtag
import com.example.letsgo.utilities.HashtagUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTaskViewModel(
    private val taskDao: TaskDao,
    private val hashtagDao: HashtagDao
)  : ViewModel() {


    private fun insertTask(vararg element: Task) = viewModelScope.launch(Dispatchers.IO) { taskDao.insert(*element) }

    private fun insertHashtag(vararg element: Hashtag) = viewModelScope.launch(Dispatchers.IO) { hashtagDao.insert(*element) }
    private fun updateHashtag(vararg element: Hashtag) = viewModelScope.launch(Dispatchers.IO) { hashtagDao.update(*element) }
    private fun fetchHashtagByID(id: Int) : LiveData<@JvmSuppressWildcards Hashtag> = hashtagDao.fetchByID(id)
    private fun fetchHashtagByName(name: String) : LiveData<@JvmSuppressWildcards Hashtag> = hashtagDao.fetchByQuery(
        SimpleSQLiteQuery("SELECT * FROM ${Hashtag.hashtagTable} WHERE name = $name")
    )

    fun createTask(
        sets: Int,
        reps: Int,
        intensity: Int,
        unit: String,
        //hashtagID: Int,
        hashtagName: String
    ) : Task {

        // HASHTAG FUNCTIONS ARE GRAYED OUT
        var hashtagID = 0

        // if hashtag does not exist, create the hashtag
        if(!hashtagName.equals(""))
            hashtagID = fetchHashtagByName(hashtagName).value?.id ?: createHashtag(hashtagName).id

        // instantiate task and insert into task database
        val creation = Task(sets, reps, "", intensity, unit, hashtagID)
        insertTask(creation)

        // assign task to hashtag and update hashtag
        if(hashtagID != 0){
            val hashtag = fetchHashtagByID(hashtagID).value!!
            hashtag.addTask(creation.id)
            updateHashtag(hashtag)
        }

        return creation

    }

    private fun createHashtag(hashtag: String) : Hashtag {

        // if hashtag is invalid, throw an error
        if(!HashtagUtils.verifyHashtag(hashtag))
            throw Error()

        // set parent ID to non-existent for now
        var parentID = 0

        //if this hashtag is a child of another, check if it exists
        val parentName = HashtagUtils.getPath(hashtag)
        if(!parentName.equals("")){

            // set parentID to this tag if it exists, otherwise create it
            parentID = fetchHashtagByName(parentName).value?.id ?: createHashtag(parentName).id

        }

        // create hashtag and insert into database (sets of child IDs and task IDs will be empty)
        val creation = Hashtag(hashtag, parentID, HashSet(), HashSet())
        insertHashtag(creation)

        // if this tag has a parent, add this tag as a child and update the parent (WHICH MUST NECESSARILY EXIST NOW)
        if(parentID != 0){

            val parentTag: Hashtag = fetchHashtagByID(parentID).value!!
            parentTag.addChild(creation.id)
            updateHashtag(parentTag)

        }

        return creation

    }

}