package com.example.roujinsns

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager

class ListActivity : AppCompatActivity() {
    var adapter: RecycleAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val database : FirebaseFirestore = FirebaseFirestore.getInstance()
        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)
        val ID  = data.getString("myID", "")
        val list = mutableListOf<databasedata>()
        if(ID.isNullOrBlank())  finish()
        // RecyclerView用のAdapterを作成
        RecycleAdapter(this, object: RecycleAdapter.OnItemClickListner{
            override fun onItemClick(item: databasedata) {

                // SecondActivityに遷移するためのIntent
                val intent = Intent(applicationContext, PageActivity::class.java)
                // RecyclerViewの要素をタップするとintentによりSecondActivityに遷移する
                // また，要素のidをSecondActivityに渡す
                intent.putExtra( "name",item.name,)
                intent.putExtra("date",item.date)
                intent.putExtra("content",item.content)
                startActivity(intent)
            }
        }).also { adapter = it }

        database.collection("roujinSNS").document(ID!!).collection("diary")//ここをUUIDにする
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val temp = databasedata(document.id,document.get("name").toString(), document.get("date").toString(), document.get("content").toString())
                    Log.d("test", "${temp.date}, ${temp.id}, ${temp.name}")
                    list.add(temp)

                }

                adapter?.setList(list)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents: ", exception)
            }

        // RecyclerViewの変数を作成してレイアウトを決定し，上で作ったadapterをセット
        val recyclerView: RecyclerView = findViewById(R.id.diaryset)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


    }
}
