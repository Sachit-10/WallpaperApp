package com.example.wallpaper

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.wallpaper.Adapter.bom_adapter
import com.example.wallpaper.Adapter.colortone_adapter
import com.example.wallpaper.Adapter.download_adapter
import com.example.wallpaper.databinding.FragmentHomeBinding
import com.example.wallpaper.DataClass.data_classItem
import com.example.wallpaper.DataClass.queryresult
import com.example.wallpaper.Retrofit.retrofit_instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Home_Fragment : Fragment() ,bom_adapter.OnClickImagelistener ,colortone_adapter.OnClickitemlistener{

    var binding: FragmentHomeBinding ?=null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)


        // to recieve the text written in search box
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                sendingimagename(p0.toString())

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })





        binding?.abstractCv?.setOnClickListener(){

            sendingimagename("Abstract")
        }

        binding?.natureCv?.setOnClickListener(){

            sendingimagename("Nature")
        }

        binding?.carCv?.setOnClickListener(){

            sendingimagename("Car")
        }

        binding?.bikeCv?.setOnClickListener(){

            sendingimagename("Bike")
        }





        //calling the api to recieve the images for best of the month
         retrofit_instance.api_interface.getdetails().enqueue(object :Callback<List<data_classItem>?>{
            override fun onResponse(
                call: Call<List<data_classItem>?>,
                response: Response<List<data_classItem>?>
            ) {
                if(response.isSuccessful){

                    val list = response.body()

                    binding?.rvBom?.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL , false)
                    binding?.rvBom?.adapter = bom_adapter(requireContext() , list!!,this@Home_Fragment)

                }
                else {
                    Toast.makeText(requireContext(), "${response.errorBody()}" , Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<List<data_classItem>?>, t: Throwable) {

                Toast.makeText(requireContext(), "${t.localizedMessage}" , Toast.LENGTH_SHORT).show()

            }


        })



        //to implement color tone recycler view
        colortonelist()


        return (binding?.root)
    }






   //implementing color tone
    fun colortonelist(){

        var colorlist = arrayOf<String>("Black" , "White","Red","Orange","Blue","Green","Yellow","Purple","Magenta")


        binding?.rvColortone?.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.HORIZONTAL, false)
        binding?.rvColortone?.adapter = colortone_adapter(requireContext() , colorlist, this@Home_Fragment)

    }





    //sending the image or searched name to view image
    fun sendingimagename(name:String){

        val intent = Intent(requireActivity() , searchview::class.java)
        intent.putExtra("searchtext" , name)
        startActivity(intent)
    }


    // when a image is clicked from best of the month
    override fun onClickImg(data: data_classItem) {       //for bom_Adapter
        val intent  = Intent(requireContext() , ViewImage::class.java)
        intent.putExtra("image" , data.urls.full)
        intent.putExtra("instagram", data.user.instagram_username)
        intent.putExtra("name", data.user.name)
        intent.putExtra("twitter", data.user.twitter_username)
        startActivity(intent)

    }


    //when color  is clicked from color tone
    override fun onclick(data: String) {                   //for colortone_Adapter
        sendingimagename(data)
    }


}