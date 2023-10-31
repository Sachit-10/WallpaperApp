package com.example.wallpaper.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaper.DataClass.data_class
import com.example.wallpaper.DataClass.data_classItem
import com.example.wallpaper.databinding.BestofmonthItemlayoutBinding

class bom_adapter(val context: Context, val bestofmonth_list:List<data_classItem>,val listner:OnClickImagelistener ):RecyclerView.Adapter<bom_adapter.viewholder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        return viewholder(BestofmonthItemlayoutBinding.inflate(LayoutInflater.from(parent.context) , parent , false))
    }

    override fun getItemCount(): Int {

        return bestofmonth_list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val photo = bestofmonth_list[position].urls.regular

        Glide.with(context).load(photo).into(holder.binding.bomImage)

        holder.itemView.setOnClickListener {

            listner.onClickImg(bestofmonth_list[position])

        }
    }


    class viewholder(var binding: BestofmonthItemlayoutBinding) : RecyclerView.ViewHolder(binding.root){}

    interface OnClickImagelistener {
        fun onClickImg(data:data_classItem)
    }

}


