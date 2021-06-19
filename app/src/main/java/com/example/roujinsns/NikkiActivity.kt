package com.example.roujinsns

import android.annotation.SuppressLint
import android.nfc.Tag
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
        val editText = findViewById<EditText>(R.id.editText)


        savebutton.setOnClickListener {
            val nikki = hashMapOf(
                "name" to editText,
                "date" to textclock,
                "content" to editText
            )


            database.collection("diaries").document("page")
                .set(nikki)
                .addOnSuccessListener { Log.d("Tag", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("Tag", "Error writing document", e) }

        }
        val kousin= database.collection("cities").document("DC")
        kousin
            .update("capital", true)
            .addOnSuccessListener { Log.d("TAG", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }




    }

    override fun finish() {
        super.finish()
    }
    }


