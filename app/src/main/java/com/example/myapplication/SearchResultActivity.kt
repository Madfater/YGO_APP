package com.example.myapplication

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Window
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class SearchResultActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var btn_finish:Button? = null
    private var imageView: ImageView?=null
    private var text_name: TextView?=null
    private var text_content: TextView?=null
    private var text_race: TextView?=null
    private var text_type: TextView?=null
    private var btn_back:Button?=null
    private var editText_No: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)


        val cards:ArrayList<CardData> = intent.extras?.getParcelableArrayList<CardData>("Cards") as ArrayList<CardData>
        btn_finish=findViewById(R.id.btn_Cardlist_finish)


        btn_finish?.setOnClickListener {
            finish()
        }

        recyclerView=findViewById(R.id.recyclerview_Cards)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        var linearLayoutManager:LinearLayoutManager=LinearLayoutManager(this)
        linearLayoutManager.isAutoMeasureEnabled=false
        var adapter=CardDataAdapter(cards)
        recyclerView.adapter=adapter

        adapter.setOnItemClickListener(object :CardDataAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                runOnUiThread {
                    show_dialog(cards[position])
                }
            }
        })
    }

    private fun show_dialog(Card:CardData)
    {
        val dialog= Dialog(this)
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

    private fun set_dialog_obj(dialog: Dialog?)
    {
        text_name=dialog?.findViewById(R.id.text_name_card_dialog)
        imageView=dialog?.findViewById(R.id.image_card_dialog)
        text_type=dialog?.findViewById(R.id.text_type_card_dialog)
        text_content=dialog?.findViewById(R.id.text_content_card_dialog)
        text_race=dialog?.findViewById(R.id.text_race_card_dialog)
        btn_back=dialog?.findViewById(R.id.btn_back_card_dialog)
    }


}