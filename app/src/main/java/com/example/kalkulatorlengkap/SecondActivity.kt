package com.example.kalkulatorlengkap

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val result = intent.getDoubleExtra("result", 0.0)
        val tvResult: TextView = findViewById(R.id.tvResult)
        tvResult.text = "Hasil: $result"

        val tvNimName: TextView = findViewById(R.id.tvNimName)
        tvNimName.text = "NIM: 225150407111053 \nNama:Hagyana Allyssa Salwa"
    }
}