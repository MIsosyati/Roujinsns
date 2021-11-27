package com.example.roujinsns

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class MainActivity : AppCompatActivity() {
    var id:String? = null
    lateinit var data:SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val editText = findViewById<EditText>(R.id.edittext1)

        val userName =editText.text.toString()

        editText.setText(userName)

        data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)
        if(data.getString("myID", "").isNullOrBlank()) {
            val myId = UUID.randomUUID().toString()
            val editor: SharedPreferences.Editor = data.edit()
            editor.putString("myID", myId)
            editor.apply()

        }

        id = data.getString("myID", "")

        button.setOnClickListener {
          val intent =Intent(application, NikkiActivity::class.java)
            startActivity(intent)

        }
        button2.setOnClickListener {
          val intent = Intent(application, FriendsActivity::class.java)
            startActivity(intent)


        }
        button3.setOnClickListener {
            val intent = Intent(application, ListActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onPause() {
        super.onPause()

        Log.d("id", id)
        if(id == null) return
        val editText = findViewById<EditText>(R.id.edittext1)
        val editor: SharedPreferences.Editor = data.edit()
        val userName =editText.text.toString()

        if(editText.text.toString() == data.getString("userName", "")) return
        editor.putString("userName",userName)
        editor.apply()




        val database: FirebaseFirestore = FirebaseFirestore.getInstance()

        val hash = hashMapOf(
            "name" to editText.text.toString()
        )

        database.collection("roujinSNS").document(id!!).set(hash)
            .addOnSuccessListener { nameadd ->
                Log.d("TAG", "name is hozoned")

            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding name", e)
            }


    }
}

