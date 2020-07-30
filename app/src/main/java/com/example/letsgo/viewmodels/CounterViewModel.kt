package com.example.letsgo.viewmodels

import android.graphics.*
import androidx.annotation.ColorInt
import androidx.core.graphics.toColor
import androidx.lifecycle.ViewModel
import com.example.letsgo.data.TaskDao
import com.example.letsgo.models.Counter
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

class CounterViewModel(
    taskID: Int,
    taskDao: TaskDao
) : ViewModel() {

    val counter: Counter

    init{

        val task = runBlocking { taskDao.fetchByIDSynchronously(taskID) }
        counter = Counter(task.sets, task.reps, task.intensity, task.unit, task.tag,
            Color.rgb( 223, 34, 34),
            Color.argb(0, 223, 34, 34)
        )    // WE WANT TO INSERT THIS INTO A DATABASE

    }

    fun increment(){

        if(counter.isInProgress())
            counter.increment()

    }

    fun updateSize(w: Int, h: Int){

        if (!counter.isBitmapInit())
            counter.bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)

        counter.canvas = Canvas(counter.bitmap)

    }

    fun drawCircle(x: Float, y: Float){

        val rad = counter.getRandomRadius()
        counter.drawCircle(x, y, rad)

    }

}