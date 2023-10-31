package com.example.wallpaper.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface image_dao {

    @Insert
    fun insert(image:image_entity)

    @Delete
    fun delete(image: image_entity)

    @Query("Select * from `image_table`")
    fun fetchAllEmployee(): LiveData<List<image_entity>>
}