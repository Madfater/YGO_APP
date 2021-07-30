package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlin.random.Random


class Dice_Fragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view =inflater.inflate(R.layout.fragment_dice, container, false)
        val now_pic=view.findViewById<ImageView>(R.id.Dice_pic)
        val last_pic=view.findViewById<ImageView>(R.id.last_dice_pic)
        var pic_id = R.drawable.ic_dice_one
        last_pic.setImageResource(0)
        now_pic.setOnClickListener {
            last_pic.setImageResource(pic_id)
            pic_id=rolling()
            now_pic.setImageResource(pic_id)
        }
        return view
    }
    private fun rolling(): Int {
        return when(Random.nextInt(1,6))
        {
            1->R.drawable.ic_dice_one
            2->R.drawable.ic_dice_two
            3->R.drawable.ic_dice_three
            4->R.drawable.ic_dice_four
            5->R.drawable.ic_dice_five
            else -> R.drawable.ic_dice_six
        }
    }
}