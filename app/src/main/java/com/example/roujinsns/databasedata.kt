package com.example.roujinsns

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
//データベースから取り出した値を入れるための変数？
open class databasedata (
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var name:String ="",
    open var date:String =""
        )