package com.example.myapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class Search_attribute_Fragment:Fragment() {

    private var spinner_type: Spinner?=null
    private var spinner_attribute: Spinner?=null
    private var spinner_race: Spinner?=null
    private var editText_name: EditText?=null
    private var editText_level: EditText?=null
    private var editText_atk: EditText?=null
    private var editText_def: EditText?=null
    private var btn_search: Button?=null
    private var btn_clear:Button?=null
    private var Cards:ArrayList<CardData> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view=inflater.inflate(R.layout.fragment_search_by_attribute, container, false)

        set_obj(view)

        btn_search?.setOnClickListener {
            var url="http://jeff3.i234.me:9487/Card?" +
                    "name=${editText_name?.text.toString()}&"+
                    "type=${spinner_type?.selectedItem.toString()}&"+
                    "level=${editText_level?.text.toString()}&"+
                    "attribute=${spinner_attribute?.selectedItem.toString()}&"+
                    "race=${spinner_race?.selectedItem.toString()}&"+
                    "atk=${editText_atk?.text.toString()}&"+
                    "DEF=${editText_def?.text.toString()}"

            Log.d("test", url)

            call_API(url)

        }

        btn_clear?.setOnClickListener {

            editText_atk?.text?.clear()
            editText_def?.text?.clear()
            editText_level?.text?.clear()
            editText_name?.text?.clear()
            spinner_attribute?.setSelection(0)
            spinner_race?.setSelection(0)
            spinner_type?.setSelection(0)

        }

        return view
    }

    override fun onPause() {
        super.onPause()
        editText_atk?.text?.clear()
        editText_def?.text?.clear()
        editText_level?.text?.clear()
        editText_name?.text?.clear()
        spinner_attribute?.setSelection(0)
        spinner_race?.setSelection(0)
        spinner_type?.setSelection(0)

    }

    private fun set_obj(view: View)
    {
        spinner_attribute=view.findViewById(R.id.attribute_spinner)
        spinner_race=view.findViewById(R.id.race_spinner)
        spinner_type=view.findViewById(R.id.type_spinner)
        editText_atk=view.findViewById(R.id.editText_atk)
        editText_def=view.findViewById(R.id.editText_def)
        editText_level=view.findViewById(R.id.editText_level)
        editText_name=view.findViewById(R.id.editText_name)
        btn_search=view.findViewById(R.id.btn_search_attribute)
        btn_clear=view.findViewById(R.id.btn_clear_attribute)
    }

    private fun format_CardData(data: JSONArray)
    {
        Cards.clear()
        for(i in 0 until data.length())
        {
            val item=data.getJSONObject(i)
            val No=item.get("No").toString()
            val name=item.get("name").toString()
            val type=item.get("type").toString()
            val level=item.get("level").toString()
            val attribute=item.get("attribute").toString()
            val race=item.get("race").toString()
            val atk=item.get("atk").toString()
            val def=item.get("def").toString()
            val Content=item.get("Content").toString()
            val Picture_url=item.get("Picture_url").toString()

            val Card=CardData(No,name,type,level,attribute, race, atk, def, Content, Picture_url)
            Cards.add(Card)
        }
    }

    private fun call_API(url:String)
    {
        val request= Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()
        val response =client.newCall(request)
        response.enqueue(
            object: Callback
            {
                override fun onFailure(call: Call, e: IOException)
                {
                    activity?.runOnUiThread{
                        Toast.makeText(context,"連接失敗", Toast.LENGTH_SHORT).show()
                        Log.d(ContentValues.TAG, e.toString())
                    }
                }
                @RequiresApi(Build.VERSION_CODES.KITKAT)
                override fun onResponse(call: Call, response: Response)
                {
                    val data= JSONArray(response.body!!.string())
                    if (data.length()>0)
                    {
                        format_CardData(data)

                        val bundle = Bundle()
                        bundle.putParcelableArrayList("Cards", Cards)

                        val intent = Intent(activity,SearchResultActivity::class.java)
                            .apply{putExtras(bundle)}

                        activity?.startActivity(intent)
                    }
                    else
                    {
                        activity?.runOnUiThread{ Toast.makeText(activity,"查無資料", Toast.LENGTH_SHORT).show()}
                    }
                }
            }
        )
    }
}