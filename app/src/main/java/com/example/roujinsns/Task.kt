package com.example.roujinsns

import java.util.*

data class Task(val id:String, val name:String, val edittext:String,val date:String){
    companion object {
        fun create(name: String): Task = Task(UUID.randomUUID().toString(),edittext,date)
    }
}
