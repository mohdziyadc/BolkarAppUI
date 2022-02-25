package com.example.bolkarappui.models

data class Moderator(
    val _id: String,
    val au: Int,
    val mic: Boolean,
    val mod: Boolean,
    val n: String,
    val socketid: String,
    val u: String,
    val visible: Boolean
)