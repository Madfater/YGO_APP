package com.example.myapplication

import android.app.Dialog
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.annotation.RequiresApi
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class Search_No_Fragment : Fragment() {

    private var imageView:ImageView?=null
    private var text_name:TextView?=null
    private var text_content:TextView?=null
    private var text_race:TextView?=null
    private var text_type:TextView?=null
    private var btn_back:Button?=null
    private var editText_No:EditText?=null
    private var btn_search: Button?=null
    private var btn_clear: Button?=null
    private var Card:CardData = CardData(null,null,null,null,null,null,null,null,null,null)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_search_by_no, container, false)

        set_obj(view)

        btn_search?.setOnClickListener {

            val url="http://jeff3.i234.me:9487/No?Card_No=${editText_No?.text}"

            Log.d("test", url)

            call_API(url)
        }

        btn_clear?.setOnClickListener {

            editText_No?.text?.clear()

        }

        return view
    }

    override fun onPause() {
        super.onPause()
        editText_No?.text?.clear()
    }

    private fun set_obj(view:View)
    {
        editText_No=view.findViewById(R.id.editText_No)
        btn_clear=view.findViewById(R.id.btn_clear_No)
        btn_search=view.findViewById(R.id.btn_search_No)
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
                    val temp=response.body!!.string()
                    if (temp!='"'+"Not Found"+'"')
                    {
                        val data = JSONObject(temp)

                        format_CardData(data)

                        activity?.runOnUiThread{show_dialog()}
                    }
                    else
                    {
                        activity?.runOnUiThread {
                            Toast.makeText(
                                activity,
                                "查無資料",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        )
    }

    private fun format_CardData(item: JSONObject)
    {
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

            Card=CardData(No,name,type,level,attribute, race, atk, def, Content, Picture_url)

    }

    private fun show_dialog()
    {
        val dialog= activity?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.dialog_card)

        set_dialog_obj(dialog)

        text_name?.text=Card.name
        text_race?.text=Card.race
        text_content?.text=Card.Content
        text_type?.text=Card.type
        text_content?.movementMethod = ScrollingMovementMethod.getInstance();

        Picasso.get().load(Card.Picture_url).into(imageView)

        btn_back?.setOnClickListener {
            dialog?.cancel()
            dialog?.dismiss()
        }

        dialog?.show()
    }

    private fun set_dialog_obj(dialog:Dialog?)
    {
        text_name=dialog?.findViewById(R.id.text_name_card_dialog)
        imageView=dialog?.findViewById(R.id.image_card_dialog)
        text_type=dialog?.findViewById(R.id.text_type_card_dialog)
        text_content=dialog?.findViewById(R.id.text_content_card_dialog)
        text_race=dialog?.findViewById(R.id.text_race_card_dialog)
        btn_back=dialog?.findViewById(R.id.btn_back_card_dialog)
    }
}