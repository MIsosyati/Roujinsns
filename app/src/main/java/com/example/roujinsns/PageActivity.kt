package com.example.roujinsns

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore


class PageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page)
        val textView8 = findViewById<TextView>(R.id.textView8)
        val textView7 = findViewById<TextView>(R.id.textView7)
        val textView9 = findViewById<TextView>(R.id.textView9)
        val intent = intent
        val date =intent.getStringExtra("date")
        val name =intent.getStringExtra("name")
        val content =intent.getStringExtra("content")
        textView8.setText("書いた日:"+date)
        textView7.setText(name)
        textView9.setText(content)

    }




}