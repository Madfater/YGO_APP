package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

data class CardData(
    var No: String?,
    var name:String?,
    var type:String?,
    var level:String?,
    var attribute:String?,
    var race:String?,
    var atk:String?,
    var def:String?,
    var Content:String?,
    var Picture_url:String?) : Parcelable
    {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        )

        override fun writeToParcel(parcel: Parcel, flags: Int)
        {
            parcel.writeString(No)
            parcel.writeString(name)
            parcel.writeString(type)
            parcel.writeString(level)
            parcel.writeString(attribute)
            parcel.writeString(race)
            parcel.writeString(atk)
            parcel.writeString(def)
            parcel.writeString(Content)
            parcel.writeString(Picture_url)
        }

        override fun describeContents(): Int
        {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<CardData>
        {
            override fun createFromParcel(parcel: Parcel): CardData
            {
                return CardData(parcel)
            }
            override fun newArray(size: Int): Array<CardData?>
            {
                return arrayOfNulls(size)
            }
        }
}
