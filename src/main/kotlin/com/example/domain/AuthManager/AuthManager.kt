package com.example.domain.AuthManager

import com.example.domain.RespondModels.LoginResponseModel

interface AuthManager {

    fun registerAccount(login:String,password:String,userAgent:String) : LoginResponseModel

    fun loginInAccount(login:String,password:String,userAgent: String) : LoginResponseModel

    fun logout(token:String,userAgent: String)
}