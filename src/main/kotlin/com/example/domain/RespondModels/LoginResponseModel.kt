package com.example.domain.RespondModels

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseModel(
    val login:String,
    val token:String
)