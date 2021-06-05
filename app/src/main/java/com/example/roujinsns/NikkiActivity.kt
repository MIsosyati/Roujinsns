package com.example.roujinsns

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.util.*


class NikkiActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nikki)
        val textclock = findViewById<TextView>(R.id.textView6)
        val localDateTime = LocalDate.now()
        textclock.setText(localDateTime.toString())
        val savebutton =findViewById<Button>(R.id.savebutton)
        val database : FirebaseFirestore= FirebaseFirestore.getInstance()

        savebutton.setOnClickListener{


        }
    }


}