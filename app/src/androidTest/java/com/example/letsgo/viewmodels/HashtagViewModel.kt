package com.example.myfirstapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.data.DataAccessObject
import com.example.myfirstapp.models.Hashtag
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HashtagViewModel(private val dao: DataAccessObject<Hashtag>) : ViewModel() {


}