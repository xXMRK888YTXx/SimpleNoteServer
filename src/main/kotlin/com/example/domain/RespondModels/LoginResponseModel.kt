package com.example.domain.RespondModels

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseModel(
    val token:String
)