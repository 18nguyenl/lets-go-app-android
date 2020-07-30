package com.example.letsgo.viewmodels

import android.graphics.*
import androidx.lifecycle.ViewModel
import com.example.letsgo.data.AchievementDao
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Achievement
import kotlinx.coroutines.runBlocking

class AchievementViewModel(
    taskID: Int,
    taskDao: TaskDao,
    private val achievementDao: AchievementDao
) : ViewModel() {

    val achievement: Achievement

    init{

        val task = runBlocking { taskDao.fetchByIDSynchronously(taskID) }
        achievement = Achievement(task.sets, task.reps, task.intensity, task.unit, task.tag,
            Color.rgb( 223, 34, 34),
            Color.argb(0, 223, 34, 34)
        )    // WE WANT TO INSERT THIS INTO A DATABASE


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