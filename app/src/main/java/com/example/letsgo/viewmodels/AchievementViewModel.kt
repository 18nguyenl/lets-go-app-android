package com.example.letsgo.viewmodels

import android.graphics.*
import androidx.lifecycle.ViewModel
import com.example.letsgo.data.AchievementDao
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Achievement
import kotlinx.coroutines.runBlocking

class AchievementViewModel(

    // WE WANT TO GET RID OF REFERENCES TO TASK AND TASKDAO EVENTUALLY
    taskID: Int,
    taskDao: TaskDao,

    // SO THAT WE CAN PULL THE ACHIEVEMENT FROM ACHIEVEMENTDAO ALREADY FULLY FORMED
    private val achievementDao: AchievementDao
) : ViewModel() {

    val achievement: Achievement

    init{

        // WE SHOULD INSTANTIATE THE ACHIEVEMENT SOMEWHERE ELSE, NOT HERE
        //      SO THAT WE CAN USE THE SAME VIEWMODEL TO OPEN EXISTING ACHIEVEMENTS
        val task = runBlocking { taskDao.fetchByIDSynchronously(taskID) }
        achievement = Achievement(task.sets, task.reps, task.intensity, task.unit, task.tag,
            Color.rgb( 223, 34, 34),
            Color.argb(0, 223, 34, 34)
        )    // WE WANT TO INSERT THIS INTO A DATABASE
        achievementDao.insert(achievement)

    }

    fun increment(){

        if(achievement.isInProgress())
            achievement.increment()

    }

    fun updateSize(w: Int, h: Int){

        if (!achievement.isBitmapInit())
            achievement.bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

        achievement.canvas = Canvas(achievement.bitmap)

    }

    fun drawCircle(x: Float, y: Float){

        val rad = achievement.getRandomRadius()
        achievement.drawCircle(x, y, rad)

    }

}