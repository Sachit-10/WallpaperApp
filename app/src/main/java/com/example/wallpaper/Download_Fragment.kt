package com.example.wallpaper

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaper.Adapter.download_adapter
import com.example.wallpaper.DataClass.data_classItem
import com.example.wallpaper.DataClass.queryresult
import com.example.wallpaper.Retrofit.retrofit_instance
import com.example.wallpaper.RoomDatabase.image_entity
import com.example.wallpaper.RoomDatabase.image_viewmodel
import com.example.wallpaper.RoomDatabase.save_adapter
import com.example.wallpaper.databinding.FragmentDownloadBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class Download_Fragment : Fragment() ,save_adapter.onclickimage{

    var binding:FragmentDownloadBinding ?=null
    private val imageviewmodel: image_viewmodel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadBinding.inflate(layoutInflater,container,false)



        imageviewmodel.getalrecords().observe(requireActivity()){

            binding?.saveRv?.layoutManager = GridLayoutManager(requireContext(), 2)
            binding?.saveRv?.adapter = save_adapter(requireContext() , it, this)
        }







        return binding?.root
    }

    override fun onclick(data: image_entity) {

        val intent = Intent(requireContext(), ViewImage::class.java)
        intent.putExtra("image", data.image)
        intent.putExtra("name", data.nameofuser)
        intent.putExtra("instagram", data.insta_id)
        intent.putExtra("twitter", data.twitter_id)
        startActivity(intent)
    }


}
