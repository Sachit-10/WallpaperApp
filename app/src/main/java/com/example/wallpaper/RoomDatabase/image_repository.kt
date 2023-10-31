package com.example.wallpaper.RoomDatabase

import androidx.lifecycle.LiveData

class image_repository (val dao:image_dao){

    fun getallrecords():LiveData<List<image_entity>>{
        return dao.fetchAllEmployee()
    }

    fun insertimage(Entity: image_entity){
        dao.insert(Entity)
    }

    fun deleteimage(Entity: image_entity){
        dao.delete(Entity)
    }
}