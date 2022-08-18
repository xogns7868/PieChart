package com.xogns.mypiechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pieChart = findViewById<PieChartView>(R.id.pie_chart)
        pieChart.setValueList(
            arrayListOf<ValueItem>(
                ValueItem("소나타", 1200f, ContextCompat.getColor(this, R.color.purple_200)),

                ValueItem("캐스퍼", 3000f, ContextCompat.getColor(this, R.color.yellow)),

                ValueItem("G70", 820f, ContextCompat.getColor(this, R.color.teal_200)),

                ValueItem("G80", 1000f, ContextCompat.getColor(this, R.color.teal_700)),

                ValueItem("G90", 500f, ContextCompat.getColor(this, R.color.white))
            )
        )
    }
}