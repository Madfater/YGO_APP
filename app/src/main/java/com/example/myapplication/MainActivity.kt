package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView

class MainActivity : AppCompatActivity() {

    companion object{
        val Dice_Fragment=com.example.myapplication.Dice_Fragment()
        val Search_Fragment=com.example.myapplication.Search_Fragment()
        val Calculator_Fragment=com.example.myapplication.Calculator_Fragment()
        val Coin_Fragment=com.example.myapplication.Coin_Fragment()
    }
    private var nevgationview: BottomNavigationView? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, Search_Fragment).commit()
        nevgationview=findViewById(R.id.nevgationview_main)
        nevgationview?.selectedItemId = R.id.f_card

        nevgationview?.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.f_card->supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, Search_Fragment).commit()
                R.id.f_dice->supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, Dice_Fragment).commit()
                R.id.f_calculator->supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, Calculator_Fragment).commit()
                R.id.f_coin->supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, Coin_Fragment).commit()
            }
            return@setOnItemSelectedListener true
        }

    }
}