package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView

class MainActivity : AppCompatActivity() {

    companion object{
        val Dice_Fragment=com.example.myapplication.Dice_Fragment()
        val Bookkeeping_Fragment=com.example.myapplication.Bookkeeping_Fragment()
        val Search_Fragment=com.example.myapplication.Search_Fragment()
        val Calculator_Fragment=com.example.myapplication.Calculator_Fragment()
    }
    private var nevgationview: BottomNavigationView? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_activity_main, Bookkeeping_Fragment)
            .commit()
        nevgationview=findViewById(R.id.nevgationview)
        nevgationview?.selectedItemId = R.id.f1
        nevgationview?.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.f1->supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, Bookkeeping_Fragment).commit()
                R.id.f2->supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, Search_Fragment).commit()
                R.id.f3->supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, Dice_Fragment).commit()
                R.id.f4->supportFragmentManager.beginTransaction().replace(R.id.container_activity_main, Calculator_Fragment).commit()
            }
            return@setOnItemSelectedListener true
        }
    }
}