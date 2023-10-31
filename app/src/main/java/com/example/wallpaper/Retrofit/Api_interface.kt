package com.example.wallpaper.Retrofit

import com.example.wallpaper.DataClass.data_classItem
import com.example.wallpaper.DataClass.queryresult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface api_interface {

    @GET("photos?client_id=${retrofit_instance.API_ID}")
    fun getdetails() : Call<List<data_classItem>>

    @GET("search/photos?client_id=${retrofit_instance.API_ID}")
    fun getimagesbyquery(
        @Query("query") query:String,
        @Query("pageno") pageno:Int
    ):Call<queryresult>


}