package com.example.roujinsns

import android.content.Intent
import android.os.Build.ID
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import androidx.recyclerview.widget.RecyclerView
import android.util.Log


class FriendsActivity : AppCompatActivity() {
    var adapter: FriendsrecycleAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        val database : FirebaseFirestore= FirebaseFirestore.getInstance()
        val list = mutableListOf<Friendsdata>()
        // RecyclerView用のAdapterを作成
        adapter = FriendsrecycleAdapter(this, object: FriendsrecycleAdapter.OnItemClickListner{
            override fun onItemClick(item: Friendsdata) {

                // SecondActivityに遷移するためのIntent
                val intent = Intent(applicationContext, PageActivity::class.java)
                // RecyclerViewの要素をタップするとintentによりSecondActivityに遷移する
                // また，要素のidをSecondActivityに渡す
                intent.putExtra( "Friendsname",item.Friendsname,)
                intent.putExtra("Friendsdate",item.FriendDate)
                intent.putExtra("content",item.content)
                startActivity(intent)
            }

        }) .also { adapter = it }


    database.collection("roujinSNS").document(""!!).collection("friend")//ここをUUIDにする
    .get()
    .addOnSuccessListener { documents ->
        for (document in documents) {
            val temp = databasedata(document.id,document.get("name").toString(), document.get("date").toString(), document.get("content").toString())
            Log.d("test", "${temp.Fr}, ${temp.id}, ${temp.name}")
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
