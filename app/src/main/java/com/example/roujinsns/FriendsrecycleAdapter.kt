package com.example.roujinsns

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

 class FriendsrecycleAdapter (private val context: Context, private val listener: FriendsrecycleAdapter.OnItemClickListner) : RecyclerView.Adapter<FriendsrecycleAdapter.FriendHolder>() {
    //RecyclerViewが所持する表示データのリスト．Realmから引き出したListをここに設定する．
    val items: MutableList<Friendsdata> = mutableListOf()

    // RecyclerViewに表示するレイアウトを引数として受け取り，コード側で保持するためのクラス
    class FriendHolder(view: View) : RecyclerView.ViewHolder(view) {
        // listofdiaryのViewを取得して変数に代入
        val container: ConstraintLayout = view.findViewById(R.id.container)
        val nameset: TextView = view.findViewById(R.id.diaryname)
        val dateset: TextView = view.findViewById(R.id.datename)
    }

    // list_item.xmlのレイアウトファイルをコード側に持ってきてViewHolderに渡す
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listofdiary, parent, false)
        return FriendHolder(view)
    }

    // RecyclerViewのn番目の要素に保持しているデータのn番目の要素を結びつける
    override fun onBindViewHolder(holder: FriendHolder, position: Int) {
        val item:
                Friendsdata = items[position]

        // MainActivity側でタップしたときの動作を記述するため，n番目の要素を渡す
        holder.container.setOnClickListener { listener.onItemClick(item) }
        // list_itemの各ViewにRealmに保存したn番目の要素の各データを表示させる
        holder.nameset.text = item.friendsname
        holder.dateset.text = item.friendsdate
    }

        override fun getItemCount(): Int {
            return items.size
        }


    // RecyclerViewの要素をタップするためのもの
    interface OnItemClickListner {
        fun onItemClick(
            //databasedataの内容を渡す
            item: Friendsdata
        )
    }
    // RecyclerViewのリスト（items）を空にして，受け取ったリスト（list）の内容に差し替える
    fun setList(list: List<Friendsdata>){
        items.clear()
        items.addAll(list)
        // RecyclerViewに要素が変わったことを通知し，再描画させる
        notifyDataSetChanged()
    }


}



