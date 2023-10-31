package com.example.wallpaper.RoomDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class image_viewmodel(application: Application): AndroidViewModel(application) {

    val repository:image_repository

    init {
        val dao = image_database.getinstance(application).imagedao()
        repository= image_repository(dao)
    }

    fun insert(item:image_entity){
        repository.insertimage(item)
    }

    fun getalrecords(): LiveData<List<image_entity>> =  repository.getallrecords()



}