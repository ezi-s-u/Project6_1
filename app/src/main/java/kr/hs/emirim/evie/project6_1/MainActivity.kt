package kr.hs.emirim.evie.project6_1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.TimePicker
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var chrono1 : Chronometer
    lateinit var btnStart : Button
    lateinit var btnDone : Button
    lateinit var rg : RadioGroup
    lateinit var radioCal : RadioButton
    lateinit var radioTime : RadioButton
    lateinit var calendar : DatePicker
    lateinit var timePick : TimePicker
    lateinit var textResult : TextView
    var selectedYear : Int = 0
    var selectedMonth : Int = 0
    var selectedDay : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chrono1 = findViewById(R.id.chrono1)
        //btnStart = findViewById(R.id.btnStart)
        //btnDone = findViewById(R.id.btnDone)
        radioCal = findViewById(R.id.radioDate)
        radioTime = findViewById(R.id.radioTime)
        rg = findViewById(R.id.rg)
        calendar = findViewById(R.id.calendar)
        timePick = findViewById(R.id.timePicker)
        textResult = findViewById(R.id.textResult)

        radioCal.visibility = View.INVISIBLE
        radioTime.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE
        calendar.visibility = View.INVISIBLE
        textResult.visibility = View.INVISIBLE

        radioCal.setOnClickListener {
            timePick.visibility = View.INVISIBLE
            calendar.visibility = View.VISIBLE
        }

        radioTime.setOnClickListener {
            timePick.visibility = View.VISIBLE
            calendar.visibility = View.INVISIBLE
        }

        chrono1.setOnClickListener {
            radioCal.visibility = View.VISIBLE
            radioTime.visibility = View.VISIBLE
            textResult.visibility = View.VISIBLE
            chrono1.base = SystemClock.elapsedRealtime()
            chrono1.start()
            chrono1.setTextColor(Color.RED)
        }

        calendar.setOnDateChangedListener { datePicker, year, month, day -> // 맨 앞에 받는 객체를 쓴다
            selectedYear = year
            selectedMonth = month
            selectedDay = day
        }

        textResult.setOnLongClickListener {
            chrono1.stop()
            chrono1.setTextColor(Color.BLUE)
            radioCal.visibility = View.INVISIBLE
            radioTime.visibility = View.INVISIBLE
            timePick.visibility = View.INVISIBLE
            calendar.visibility = View.INVISIBLE
            textResult.visibility = View.INVISIBLE
            textResult.setText(""+ selectedYear + "년 " + selectedMonth +"월 "+selectedDay+"일 ") //맨 앞에 정수가 오면 error
            textResult.append("" + timePick.currentHour + "시 " + timePick.currentMinute + "분 ")
            textResult.append("예약됨")
            true
        }
    }
}