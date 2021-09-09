package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardDataAdapter (private val cardList:ArrayList<CardData>):RecyclerView.Adapter<CardDataAdapter.MyViewHolder>() {

    private lateinit var mlistener: onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mlistener=listener
    }

    class MyViewHolder(itemView:View, listener: onItemClickListener):RecyclerView.ViewHolder(itemView)
    {
        val text_name:TextView=itemView.findViewById(R.id.item_text_name)
        val text_level:TextView=itemView.findViewById(R.id.item_text_level)
        val text_type:TextView=itemView.findViewById(R.id.item_text_type)
        val text_atk:TextView=itemView.findViewById(R.id.item_text_atf)
        val text_def:TextView=itemView.findViewById(R.id.item_text_def)
        val image_attribute:ImageView=itemView.findViewById(R.id.item_image_attribute)
        val text_race:TextView=itemView.findViewById(R.id.item_text_race)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDataAdapter.MyViewHolder
    {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return MyViewHolder(itemView,mlistener)
    }

    override fun onBindViewHolder(holder: CardDataAdapter.MyViewHolder, position: Int)
    {
        val currentItem=cardList[position]

        holder.text_name.text=currentItem.name
        holder.text_atk.text=if(currentItem.atk!="") currentItem.atk+"ATK" else currentItem.atk
        holder.text_def.text=if (currentItem.def!="") currentItem.def+"DEF" else currentItem.def
        holder.text_level.text= if(currentItem.level?.length!! in 1..2) "等級"+currentItem.level else currentItem.level
        holder.text_race.text=currentItem.race
        holder.text_type.text=currentItem.type
        holder.image_attribute.setImageResource(get_pic(currentItem.attribute))


    }

    override fun getItemCount(): Int
    {
        return cardList.size
    }

    private fun get_pic(text:String?):Int
    {
        return (when(text)
                {
                    "光"->R.drawable.ic_light
                    "闇"->R.drawable.ic_dark
                    "地"->R.drawable.ic_earth
                    "炎"->R.drawable.ic_fire
                    "水"->R.drawable.ic_water
                    "風"->R.drawable.ic_wind
                    "神"->R.drawable.ic_god
                    "魔"->R.drawable.ic_magic
                    "罠"->R.drawable.ic_trap
                    else -> R.drawable.ic_dark

                })
    }
}