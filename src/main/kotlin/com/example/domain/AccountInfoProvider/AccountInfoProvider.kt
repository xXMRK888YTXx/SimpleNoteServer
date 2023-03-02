package com.example.domain.AccountInfoProvider

import com.example.Database.entity.AccountEntity
import com.example.Database.entity.TokenEntity

interface AccountInfoProvider {
    fun getAccountDataByToken(token:String,userAgent:String) : AccountEntity
}