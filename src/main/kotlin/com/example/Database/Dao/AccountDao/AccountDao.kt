package com.example.Database.Dao.AccountDao

import com.example.Database.entity.AccountEntity

interface AccountDao {

    fun insertAccount(accountEntity: AccountEntity) : Int

    fun getAccountByLogin(login: String) : AccountEntity?

    fun getAccountById(id: Int) : AccountEntity?

    fun isAccountExist(id:Int) : Boolean

    fun isAccountExist(login:String) : Boolean

}