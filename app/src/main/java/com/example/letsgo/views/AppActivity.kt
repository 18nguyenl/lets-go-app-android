package com.example.letsgo.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.letsgo.R
import com.example.letsgo.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity() {
    // Databinding Object for AppActivity
    private lateinit var binding: ActivityAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityAppBinding>(this,
            R.layout.activity_app
        )

        // ActionBar Setup
        setSupportActionBar(binding.toolbar)
    }
}