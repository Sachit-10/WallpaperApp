package com.example.wallpaper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wallpaper.Adapter.download_adapter
import com.example.wallpaper.DataClass.data_classItem
import com.example.wallpaper.DataClass.queryresult
import com.example.wallpaper.Retrofit.retrofit_instance
import com.example.wallpaper.databinding.ActivitySearchviewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class searchview : AppCompatActivity() , download_adapter.onclickimagelistener{

    var binding:ActivitySearchviewBinding ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchviewBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //setting up back button
        setSupportActionBar(binding?.searchToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.searchToolbar?.setNavigationOnClickListener(){
            val intent = Intent(this,Home_Fragment::class.java)
            startActivity(intent)
        }


        //receiving searched name of image
        val searchtext = intent.getStringExtra("searchtext")
        binding?.name?.text = searchtext


        //calling the api to get the required images
        retrofit_instance.api_interface.getimagesbyquery(searchtext.toString() , 1).enqueue(object : Callback<queryresult> {
            override fun onResponse(call: Call<queryresult>, response: Response<queryresult>) {

                if(response.isSuccessful){

                    val result = response.body()!!
                    val array:Array<data_classItem> = result.results

                    binding?.searchviewRv?.layoutManager = GridLayoutManager(this@searchview , 2)
                    binding?.searchviewRv?.adapter = download_adapter(this@searchview , array.toList() ,this@searchview )

                }
                else {
                    Toast.makeText(this@searchview , "Failure", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<queryresult>, t: Throwable) {
                Toast.makeText(this@searchview , "${t.localizedMessage}", Toast.LENGTH_LONG).show()

            }

        })


    }


   //when someone clicked a image in searched images
    override fun onclickphoto(data: data_classItem) {

        val intent  = Intent(this, ViewImage::class.java)
        intent.putExtra("image" , data.urls.full)
        intent.putExtra("name" , data.user.name)
        intent.putExtra("instagram" , data.user.instagram_username)
        intent.putExtra("twitter" , data.user.twitter_username)

        startActivity(intent)
    }
}