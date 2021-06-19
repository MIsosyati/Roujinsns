package com.example.roujinsns

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

class SetteiActivity : AppCompatActivity() {
    var adapter: RecycleAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settei)
        val database : FirebaseFirestore = FirebaseFirestore.getInstance()
        val setdiary = findViewById<RecyclerView>(R.id.diaryset)



         database.collection("diaries")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }

        // RecyclerView用のAdapterを作成
        adapter = RecycleAdapter(this, object: RecycleAdapter.OnItemClickListner{
            override fun onItemClick(item: databasedata) {
                // SecondActivityに遷移するためのIntent
                //val intent = Intent(applicationContext, SecondActivity::class.java)
                // RecyclerViewの要素をタップするとintentによりSecondActivityに遷移する
                // また，要素のidをSecondActivityに渡す
//                intent.putExtra("id", item.id)
//                startActivity(intent)
            }
        })

        // RecyclerViewの変数を作成してレイアウトを決定し，上で作ったadapterをセット
        val recyclerView: RecyclerView = findViewById(R.id.diaryset)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter



    }

    override fun onResume() {
        super.onResume()

    }




}
