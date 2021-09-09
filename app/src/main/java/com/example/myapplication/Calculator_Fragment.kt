package com.example.myapplication

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*
import kotlin.concurrent.timer

class Calculator_Fragment : Fragment() {

    private var number:Int = 0
    private var time = 3600
    private var isRunning = false
    private var timerTask: Timer? = null
    private var btn_start:ImageButton?=null
    private var btn_reset:ImageButton?=null
    private var btn_plus:Button?=null
    private var btn_div:Button?=null
    private var btn_minus:Button?=null
    private var btn_multiply:Button?=null
    private var btn_lp_reset:Button?=null
    private var btn_one: Button? = null
    private var btn_two: Button? = null
    private var btn_three: Button? = null
    private var btn_four: Button? = null
    private var btn_five: Button? = null
    private var btn_six: Button? = null
    private var btn_seven: Button? = null
    private var btn_eight: Button? = null
    private var btn_nine: Button? = null
    private var btn_backspace: ImageButton? = null
    private var btn_zero: Button? = null
    private var btn_double_zero: Button? = null
    private var btn_all_clear: Button? = null
    private var btn_confirm: Button? = null
    private var btn_cancel: Button? = null
    private var text_output:TextView?=null
    private var text_time:TextView?=null
    private var player:String?="player1"
    private var text_player1:TextView?=null
    private var text_player2:TextView?=null
    private var player1_lp:Int=8000
    private var player2_lp:Int=8000

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view=inflater.inflate(R.layout.fragment_calculator, container, false)
        set_obj(view)
        init()
        btn_press()
        text_press()
        return view
    }

    private fun init()
    {
        text_player1?.isSelected=true
        text_player2?.isSelected=false
        if(isRunning)
        {
            btn_start?.setImageResource(R.drawable.ic_pause_black_24dp)
            text_time?.text = format_time(time)
        }
        else
        {
            btn_start?.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            text_time?.text = format_time(time)
        }
        text_player1?.text=player1_lp.toString()
        text_player2?.text=player2_lp.toString()
    }

    private fun text_press()
    {
        text_player1?.setOnClickListener{
            player="player1"
            text_player1?.isSelected=true
            text_player2?.isSelected=false
        }
        text_player2?.setOnClickListener {
            player="player2"
            text_player1?.isSelected=false
            text_player2?.isSelected=true
        }
    }


    private fun btn_press()
    {
        btn_plus?.setOnClickListener {
            show_dialog("+")
        }
        btn_minus?.setOnClickListener {
            show_dialog("-")
        }
        btn_multiply?.setOnClickListener {
            show_dialog("*")
        }
        btn_div?.setOnClickListener {
            show_dialog("/")
        }
        btn_start?.setOnClickListener {
            isRunning=!isRunning
            if (isRunning)
                start()
            else
                pause()
        }
        btn_reset?.setOnClickListener {
            reset()
        }
        btn_lp_reset?.setOnClickListener {
            player1_lp=8000
            player2_lp=8000
            init()
        }
    }

    private fun format_time(time:Int): String {
        val min=time/60
        val sec=time%60
        val min_format=if(min>=10) "$min" else "0$min"
        val sec_format=if(sec>=10) "$sec" else "0$sec"
        return "$min_format:$sec_format"
    }

    private  fun  set_obj(view: View)
    {
        btn_lp_reset=view.findViewById(R.id.btn_lp_reset)
        btn_reset=view.findViewById(R.id.btn_reset)
        btn_start=view.findViewById(R.id.btn_start)
        btn_div=view.findViewById(R.id.btn_divide)
        btn_minus=view.findViewById(R.id.btn_minus)
        btn_multiply=view.findViewById(R.id.btn_multiply)
        btn_plus=view.findViewById(R.id.btn_plus)
        text_player1=view.findViewById(R.id.text_lp_player1)
        text_player2=view.findViewById(R.id.text_lp_player2)
        text_time=view.findViewById(R.id.text_time)
    }

    private fun show_dialog(string: String)
    {
        val dialog= activity?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.dialog_calculator)
        set_dialog_obj(dialog)
        btn_one?.setOnClickListener {
            dialog_output_change("1")
        }
        btn_two?.setOnClickListener {
            dialog_output_change("2")
        }
        btn_three?.setOnClickListener {
            dialog_output_change("3")
        }
        btn_four?.setOnClickListener {
            dialog_output_change("4")
        }
        btn_five?.setOnClickListener {
            dialog_output_change("5")
        }
        btn_six?.setOnClickListener {
            dialog_output_change("6")
        }
        btn_seven?.setOnClickListener {
            dialog_output_change("7")
        }
        btn_eight?.setOnClickListener {
            dialog_output_change("8")
        }
        btn_nine?.setOnClickListener {
            dialog_output_change("9")
        }
        btn_zero?.setOnClickListener {
            dialog_output_change("0")
        }
        btn_double_zero?.setOnClickListener {
            dialog_output_change("00")
        }
        btn_backspace?.setOnClickListener {
            dialog_output_change("backspace")
        }
        btn_all_clear?.setOnClickListener {
            number = 0
            text_output?.text=number.toString()
        }
        btn_confirm?.setOnClickListener {
            when(string)
            {
                "+"->{
                   when(player)
                   {
                       "player1"->player1_lp+=number
                       "player2"->player2_lp+=number
                   }
                }
                "-"->{
                    when(player)
                    {
                        "player1"->player1_lp-=number
                        "player2"->player2_lp-=number
                    }
                }
                "*"->{
                    when(player)
                    {
                        "player1"->player1_lp*=number
                        "player2"->player2_lp*=number
                    }
                }
                "/"->{
                    when(player)
                    {
                        "player1"->player1_lp/=number
                        "player2"->player2_lp/=number
                    }
                }
            }
            text_player1?.text=player1_lp.toString()
            text_player2?.text=player2_lp.toString()
            number=0
            dialog?.cancel()
            dialog?.dismiss()
        }
        btn_cancel?.setOnClickListener {
            dialog?.cancel()
            dialog?.dismiss()
        }
        dialog?.show()
    }

    private fun dialog_output_change(string:String)
    {
        when(string)
        {
            "1"->{
                if(number==0)
                    number=1
                else
                {
                    number*=10
                    number++
                }
            }
            "2"->{
                if(number==0)
                    number=2
                else
                {
                    number*=10
                    number+=2
                }
            }
            "3"->{
                if(number==0)
                    number=3
                else
                {
                    number*=10
                    number+=3
                }
            }
            "4"->{
                if(number==0)
                    number=4
                else
                {
                    number*=10
                    number+=4
                }
            }
            "5"->{
                if(number==0)
                    number=5
                else
                {
                    number*=10
                    number+=5
                }
            }
            "6"->{
                if(number==0)
                    number=6
                else
                {
                    number*=10
                    number+=6
                }
            }
            "7"->{
                if(number==0)
                    number=7
                else
                {
                    number*=10
                    number+=7
                }
            }
            "8"->{
                if(number==0)
                    number=8
                else
                {
                    number*=10
                    number+=8
                }
            }
            "9"->{
                if(number==0)
                    number=9
                else
                {
                    number*=10
                    number+=9
                }
            }
            "0"->{
                if(number!=0)
                    number*=10
            }
            "00"->{
                if (number!=0)
                    number*=100
            }
            "backspace"->{
                if (number!=0)
                    number/=10
            }
        }
        text_output?.text = number.toString()
    }

    private fun set_dialog_obj(dialog: Dialog?)
    {
        text_output=dialog?.findViewById(R.id.text_output)
        btn_one= dialog?.findViewById(R.id.btn_one)
        btn_two= dialog?.findViewById(R.id.btn_two)
        btn_three = dialog?.findViewById(R.id.btn_three)
        btn_four = dialog?.findViewById(R.id.btn_four)
        btn_five = dialog?.findViewById(R.id.btn_five)
        btn_six = dialog?.findViewById(R.id.btn_six)
        btn_seven = dialog?.findViewById(R.id.btn_seven)
        btn_eight = dialog?.findViewById(R.id.btn_eight)
        btn_nine = dialog?.findViewById(R.id.btn_nine)
        btn_backspace = dialog?.findViewById(R.id.btn_backspace)
        btn_zero = dialog?.findViewById(R.id.btn_zero)
        btn_double_zero = dialog?.findViewById(R.id.btn_double_zero)
        btn_all_clear = dialog?.findViewById(R.id.btn_clear)
        btn_confirm = dialog?.findViewById(R.id.btn_confirm)
        btn_cancel= dialog?.findViewById(R.id.btn_cancel)
    }

    private fun pause()
    {
        btn_start?.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel()
    }

    private fun start()
    {
        btn_start?.setImageResource(R.drawable.ic_pause_black_24dp)
        timerTask = timer(period=1000)
        {
            time--
            activity?.runOnUiThread {
                text_time?.text = format_time(time)
            }
        }
    }

    private fun reset()
    {
        timerTask?.cancel()
        time=3600
        isRunning=false
        btn_start?.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        text_time?.text="60:00"
    }
}