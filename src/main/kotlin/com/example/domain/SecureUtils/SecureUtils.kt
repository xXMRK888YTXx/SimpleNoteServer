package com.example.domain.SecureUtils

interface SecureUtils {

    fun getHash(data:ByteArray) : String

    fun getHash(string: String) : String

    fun generateSalt() : String

    fun generateToken() : String
}