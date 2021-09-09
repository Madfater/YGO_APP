package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView


class Search_Fragment : Fragment() {

    companion object{
        val Search_attribute_Fragment=com.example.myapplication.Search_attribute_Fragment()
        val Search_No_Fragment=com.example.myapplication.Search_No_Fragment()
    }
    private var nevgationview: BottomNavigationView? =null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_search, container, false)

        childFragmentManager.beginTransaction().replace(R.id.container_activity_card, Search_No_Fragment).commit()
        nevgationview=view.findViewById(R.id.nevgationview_main_card)
        nevgationview?.selectedItemId = R.id.f_No

        nevgationview?.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.f_No->childFragmentManager.beginTransaction().replace(R.id.container_activity_card, Search_No_Fragment).commit()

                R.id.f_attribute->childFragmentManager.beginTransaction().replace(R.id.container_activity_card, Search_attribute_Fragment).commit()

            }
            return@setOnItemSelectedListener true
        }

        return view
    }

    override fun onPause() {
        super.onPause()
        nevgationview?.selectedItemId = R.id.f_No
        childFragmentManager.beginTransaction().replace(R.id.container_activity_card, Search_No_Fragment).commit()

    }

}