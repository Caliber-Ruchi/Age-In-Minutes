package com.example.ageinminutes



import android.app.DatePickerDialog
import android .widget.DatePicker
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var tvSelectedDate:TextView?=null

    private var tvAgeInMinutes:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.ageInMinutes)
    }
    private fun clickDatePicker(){
        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,selectedyear,selectedmonth,selecteddayOfMonth->
                Toast.makeText(this,"Year was $selectedyear , month was ${selectedmonth+1},day of month was $selecteddayOfMonth "
                    ,Toast.LENGTH_LONG).show()
                val selectedDate="$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
                tvSelectedDate?.text=selectedDate
                val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes=theDate.time/60000
                    val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {   val currentDateInMinutes=currentDate.time/60000
                        val differenceInMinutes=currentDateInMinutes-selectedDateInMinutes
                        tvAgeInMinutes?.text=differenceInMinutes.toString()
                    }

                }

            },
            year,
            month,
            day,
        )
        dpd.datePicker.maxDate=System.currentTimeMillis()
        dpd.show()


    }


}