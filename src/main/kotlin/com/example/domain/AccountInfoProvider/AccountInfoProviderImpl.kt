package com.example.domain.AccountInfoProvider

import com.example.Database.Dao.AccountDao.AccountDao
import com.example.Database.Dao.TokenDao.TokenDao
import com.example.Database.entity.AccountEntity
import com.example.exceptions.AccountNotFoundException
import com.example.exceptions.SessionExpressionException

class AccountInfoProviderImpl(
    private val tokenDao: TokenDao,
    private val accountDao: AccountDao
) : AccountInfoProvider {

    override fun getAccountDataByToken(token: String, userAgent: String): AccountEntity {
        val tokenInfo = tokenDao.findToken(token, userAgent)
            ?: throw SessionExpressionException()

        return accountDao.getAccountById(tokenInfo.accountId)
            ?: throw AccountNotFoundException()
    }
}