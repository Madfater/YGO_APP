package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class Coin_Fragment : Fragment() {

    private var coin:ImageView?=null
    private var text_coin:TextView?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_coin, container, false)
        set_obj(view)
        tap_coin()
        return view
    }

    private fun set_obj(view: View)
    {
        coin=view.findViewById(R.id.imgCoin)
        text_coin=view.findViewById(R.id.text_coin)
    }

    private fun tap_coin()
    {
        coin?.setOnClickListener {
            val random=(1..2).random()

            if (random==1)
                flip_coin(R.drawable.ic_icon_heads,"正面")
            else
                flip_coin(R.drawable.ic_icon_tails,"反面")
        }
    }

    private fun flip_coin(imageId: Int,coinSide: String)
    {
        coin?.animate()?.apply {
            duration=1000
            rotationYBy(1800f)
            coin?.isClickable=false
        }?.withEndAction {
            coin?.setImageResource(imageId)
            text_coin?.text=coinSide
            coin?.isClickable=true
        }
    }
}