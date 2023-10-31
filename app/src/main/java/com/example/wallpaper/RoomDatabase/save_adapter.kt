package com.example.wallpaper.RoomDatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaper.DataClass.data_classItem
import com.example.wallpaper.databinding.BestofmonthItemlayoutBinding
import com.example.wallpaper.databinding.SearchimageLayoutBinding

class save_adapter(val context: Context, val data:List<image_entity>, val listener: onclickimage):RecyclerView.Adapter<save_adapter.viewholder>() {


    class viewholder(val binding:SearchimageLayoutBinding):RecyclerView.ViewHolder(binding.root){}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(SearchimageLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val photo = data[position]

        Glide.with(context).load(photo.image).into(holder.binding.bomImage)

        holder.itemView.setOnClickListener(){
            listener.onclick(photo)
        }

    }

    interface onclickimage {

        fun onclick(data:image_entity)

    }

}