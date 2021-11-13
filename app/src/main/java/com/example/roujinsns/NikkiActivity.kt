package com.example.roujinsns

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate


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
        val editcontent = findViewById<EditText>(R.id.editcontent)
        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)
        val ID  = data.getString("myID", "")

        if(ID.isNullOrBlank())  finish()



        savebutton.setOnClickListener {
            val nikki = hashMapOf(
                "name" to editText.text.toString(),
                "date" to textclock.text.toString(),
                "content" to editcontent.text.toString()
            )

            database.collection("roujinSNS").document(ID!!).collection("diary")
                .add(nikki)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
                    finish()
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }

       }
    }
}
