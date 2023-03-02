package com.example.domain.RespondModels

import com.example.exceptions.ServerException
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseModel(
    val errorCode:String
) {
    constructor(e:ServerException) : this(e.errorCode)
}