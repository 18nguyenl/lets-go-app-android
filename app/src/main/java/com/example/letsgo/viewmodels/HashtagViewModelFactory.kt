package com.example.letsgo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.letsgo.data.DataAccessObject
import com.example.letsgo.models.Hashtag

class HashtagViewModelFactory(private val dao: DataAccessObject<Hashtag>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = HashtagViewModel(dao) as T

}