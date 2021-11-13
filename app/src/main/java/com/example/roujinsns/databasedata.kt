package com.example.roujinsns

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
//データベースから取り出した値を入れるための変数？
class databasedata (
    var id: String,
    var name:String,
    var date:String,
    var content: String
)