package com.example.roujinsns

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)

       // val message = prefs.getString("message", "not found")



        if(data.getString("myID", "").isNullOrBlank()) {
            val myId = UUID.randomUUID().toString()
            val editor: SharedPreferences.Editor = data.edit()
            editor.putString("myID", myId)
            editor.apply()
        }

        button.setOnClickListener {
          val intent =Intent(application, NikkiActivity::class.java)
            startActivity(intent)

        }
//        button2.setOnClickListener {
//            Intent(application, FamilyActivity::class.java)
//            startActivity(intent)
//
//
//        }
        button3.setOnClickListener {
            val intent = Intent(application, ListActivity::class.java)
            startActivity(intent)

        }
    }


}

