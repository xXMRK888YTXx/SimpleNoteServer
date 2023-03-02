package com.example.Database.Dao.TokenDao

import com.example.Database.entity.TokenEntity

interface TokenDao {
    fun insertToken(tokenEntity: TokenEntity)

    fun findToken(id:Int, userAgent:String) : TokenEntity?

    fun findToken(token:String, userAgent: String) : TokenEntity?

    fun removeToken(id:Int,userAgent: String)
}