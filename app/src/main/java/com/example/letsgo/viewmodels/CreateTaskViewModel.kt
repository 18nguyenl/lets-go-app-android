package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letsgo.models.Task
import com.example.letsgo.data.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTaskViewModel(
    private val taskDao: TaskDao
)  : ViewModel() {


    private fun insertTask(vararg element: Task) = viewModelScope.launch(Dispatchers.IO) { taskDao.insert(*element) }

    fun createTask(
        sets: Int,
        reps: Int,
        intensity: Int,
        unit: String,
        hashtagID: Int
    ) : Task {

        // HASHTAG FUNCTIONS ARE GRAYED OUT

        // if hashtag does not exist, create the hashtag
        //if(!hashtagName.equals(""))
        //    hashtagID = fetchHashtagByName(hashtagName).value?.id ?: createHashtag(hashtagName).id

        // instantiate task and insert into task database
        val creation = Task(sets, reps, "", intensity, unit, hashtagID)
        insertTask(creation)

        // assign task to hashtag and update hashtag
        //hashtag.addTask(creation.id)
        //update(hashtag)

        return creation

    }

//    private fun createHashtag(hashtag: String) : Hashtag {
//
//        // if hashtag is invalid, throw an error
//        if(!HashtagUtils.verifyHashtag(hashtag))
//            throw Error()
//
//        // set parent ID to non-existent for now
//        var parentID: Int = 0
//
//        //if this hashtag is a child of another, check if it exists
//        val parentName = HashtagUtils.getPath(hashtag)
//        if(!parentName.equals("")){
//
//            // set parentID to this tag if it exists, otherwise create it
//            parentID = fetchHashtagByName(parentName).value?.id ?: createHashtag(parentName).id
//
//        }
//
//        // create hashtag and insert into database (sets of child IDs and task IDs will be empty)
//        val creation = Hashtag(hashtag, parentID, HashSet(), HashSet())
//        insertHashtag(creation)
//
//        // if this tag has a parent, add this tag as a child and update the parent (WHICH MUST NECESSARILY EXIST NOW)
//        if(parentID != 0){
//
//            val parentTag: Hashtag = fetchHashtagByID(parentID).value!!
//            parentTag.addChild(creation.id)
//            updateHashtag(parentTag)
//
//        }
//
//        return creation
//
//    }

}