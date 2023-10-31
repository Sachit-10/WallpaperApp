package com.example.wallpaper.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofit_instance {

    const val API_ID:String = "N2__TvKrjMhXRQm4MBpXMi5U99e2P7SF1kzTT6yk1A4"

    val retrofit by lazy {

        Retrofit.Builder().baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api_interface by lazy {
        retrofit.create(com.example.wallpaper.Retrofit.api_interface::class.java)
    }




}