package com.example.wallpaper.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaper.DataClass.data_classItem
import com.example.wallpaper.DataClass.queryresult
import com.example.wallpaper.databinding.BestofmonthItemlayoutBinding
import com.example.wallpaper.databinding.SearchimageLayoutBinding

class download_adapter(var context: Context, var list :List<data_classItem>, var listner:onclickimagelistener):RecyclerView.Adapter<download_adapter.viewholder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        return viewholder(SearchimageLayoutBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        var image = list[position]
        Glide.with(context).load(image.urls.regular).into(holder.binding.bomImage)

        holder.itemView.setOnClickListener(){
            listner.onclickphoto(image)
        }
    }

    interface onclickimagelistener {

        fun onclickphoto(data:data_classItem)

    }

    class viewholder(var binding: SearchimageLayoutBinding):RecyclerView.ViewHolder(binding.root){}



}