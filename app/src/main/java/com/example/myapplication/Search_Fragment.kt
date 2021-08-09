package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Search_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_search, container, false)
        val queue = Volley.newRequestQueue(activity?.applicationContext)
        val url = "https://localhost:44344/api/Card/Data?Card_atk=0"
        var textView=view.findViewById<TextView>(R.id.textView)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            Response.ErrorListener { textView.text = "That didn't work!" })
        queue.add(stringRequest)
        return view
    }

}