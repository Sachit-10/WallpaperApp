package com.example.wallpaper.RoomDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallpaper.DataClass.data_classItem

@Entity(tableName = "image_table")
data class image_entity(

    @PrimaryKey
    val id:Int?=null,

    val image:String,
    val nameofuser:String,
    val insta_id:String,
    val twitter_id:String

)
