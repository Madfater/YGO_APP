package com.example.myapplication

 class DataTransfer {

     companion object{
         private var data:ArrayList<CardData> = ArrayList()
     }

    fun getData(): ArrayList<CardData> {
        return data
    }

    fun setData(datas:ArrayList<CardData>)
    {
        data=datas
    }
}