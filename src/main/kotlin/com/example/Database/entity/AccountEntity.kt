package com.example.Database.entity

data class AccountEntity(
    val id:Int = 0,
    val login:String,
    val hashPassword:String,
    val salt:String
)