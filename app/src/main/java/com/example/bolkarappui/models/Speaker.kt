package com.example.bolkarappui.models

data class Speaker(
    val _id: String,
    val viewType:Int = 2,
    val au: Int,
    val mic: Boolean,
    val mod: Boolean,
    val n: String,
    val socketid: String,
    val u: String,
    val visible: Boolean
)