package com.example.roujinsns

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import androidx.recyclerview.widget.RecyclerView
import android.util.Log


class FriendsActivity : AppCompatActivity() {
    var adapter: RecycleAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        val database : FirebaseFirestore= FirebaseFirestore.getInstance()
        val list = mutableListOf<Friendsdata>()
        // RecyclerView用のAdapterを作成
        adapter = RecycleAdapter(this, object: RecycleAdapter.OnItemClickListner{
            override fun onItemClick(item: Friendsdata) {

                // SecondActivityに遷移するためのIntent
                val intent = Intent(applicationContext, PageActivity::class.java)
                // RecyclerViewの要素をタップするとintentによりSecondActivityに遷移する
                // また，要素のidをSecondActivityに渡す
                intent.putExtra( "Friendsname",item.Friendsname,)
                intent.putExtra("date",item.date)
                intent.putExtra("content",item.content)
                startActivity(intent)
            }

        })
    }
}