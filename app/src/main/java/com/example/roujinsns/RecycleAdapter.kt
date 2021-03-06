package com.example.roujinsns

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

 class RecycleAdapter(private val context: Context, private val listener: OnItemClickListner) : RecyclerView.Adapter<RecycleAdapter.MyViewHolder>() {
    //RecyclerViewが所持する表示データのリスト．Realmから引き出したListをここに設定する．
    val items: MutableList<databasedata> = mutableListOf()


    // RecyclerViewに表示するレイアウトを引数として受け取り，コード側で保持するためのクラス
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // listofdiaryのViewを取得して変数に代入
        val container: ConstraintLayout = view.findViewById(R.id.container)
        val nameset: TextView = view.findViewById(R.id.diaryname)
        val dateset: TextView = view.findViewById(R.id.datename)
    }
    // list_item.xmlのレイアウトファイルをコード側に持ってきてViewHolderに渡す
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listofdiary, parent, false)
        return MyViewHolder(view)
    }
    // RecyclerViewのn番目の要素に保持しているデータのn番目の要素を結びつける
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item:
                databasedata= items[position]

        // MainActivity側でタップしたときの動作を記述するため，n番目の要素を渡す
        holder.container.setOnClickListener { listener.onItemClick(item) }
        // list_itemの各ViewにRealmに保存したn番目の要素の各データを表示させる
        holder.nameset.text = item.name
        holder.dateset.text = item.date


    }

     override fun getItemCount(): Int {
         return items.size
     }
     // RecyclerViewの要素をタップするためのもの
     interface OnItemClickListner{
         fun onItemClick(
             //databasedataの内容を渡す
             item:databasedata)
     }
     // RecyclerViewのリスト（items）を空にして，受け取ったリスト（list）の内容に差し替える
     fun setList(list: List<databasedata>){
         items.clear()
         items.addAll(list)
         // RecyclerViewに要素が変わったことを通知し，再描画させる
         notifyDataSetChanged()
     }


 }