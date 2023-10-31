package com.example.wallpaper.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaper.DataClass.data_class
import com.example.wallpaper.DataClass.data_classItem
import com.example.wallpaper.databinding.ColortoneItemlayoutBinding

class colortone_adapter(val context: Context , var colortone_list:Array<String>, val listener:OnClickitemlistener):RecyclerView.Adapter<colortone_adapter.viewholder>() {

    class viewholder(var binding: ColortoneItemlayoutBinding):RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        return viewholder(ColortoneItemlayoutBinding.inflate(LayoutInflater.from(parent.context), parent , false))
    }

    override fun getItemCount(): Int {
        return colortone_list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        var colorname = colortone_list[position]
        var colorcode:String?=null

        when(colorname) {

            "Black" -> colorcode = "#000000"
            "White" -> colorcode = "#FFFFFF"
            "Blue" -> colorcode = "#0000FF"
            "Red" -> colorcode = "#FF0000"
            "Green" -> colorcode = "#00FF00"
            "Yellow" -> colorcode = "#FFFF00"
            "Magenta" -> colorcode = "#FF00FF"
            "Purple" -> colorcode = "#800080"
            "Teal" -> colorcode = "#008080"
            "Orange" -> colorcode = "#FF8C00"

        }

        holder.binding.itemColor.setCardBackgroundColor(Color.parseColor(colorcode))

        holder.itemView.setOnClickListener(){

            listener.onclick(colorname)

        }
    }


interface OnClickitemlistener {

    fun onclick(data: String)
}



}
