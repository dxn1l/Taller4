package com.example.taller4.Database

data class User(
    var id: String? = null,
    val name: String,
    val last_name: String,
    val direction: String,
    val phone: String,
    val email: String,
    val age: Int,
    val gender: String,
)