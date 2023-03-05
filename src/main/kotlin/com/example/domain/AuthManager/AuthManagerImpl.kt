package com.example.domain.AuthManager

import com.example.Database.Dao.AccountDao.AccountDao
import com.example.Database.Dao.TokenDao.TokenDao
import com.example.Database.entity.AccountEntity
import com.example.Database.entity.TokenEntity
import com.example.domain.SecureUtils.SecureUtils
import com.example.domain.RespondModels.LoginResponseModel
import com.example.exceptions.AccountAlreadyExistException
import com.example.exceptions.AccountNotFoundException
import com.example.exceptions.InvalidPasswordException
import com.example.exceptions.SessionExpressionException

class AuthManagerImpl(
    private val accountDao: AccountDao,
    private val tokenDao: TokenDao,
    private val secureUtils: SecureUtils
) : AuthManager {
    override fun registerAccount(login: String, password: String, userAgent: String): LoginResponseModel {
        if (accountDao.isAccountExist(login.lowercase())) {
            throw AccountAlreadyExistException()
        }

        val salt = secureUtils.generateSalt()
        val passwordHash = secureUtils.getHash(password + salt)

        val id = accountDao.insertAccount(AccountEntity(
            login = login.lowercase(),
            hashPassword = passwordHash,
            salt = salt))

        val token = secureUtils.generateToken()
        tokenDao.insertToken(TokenEntity(id, token, userAgent))

        return LoginResponseModel(login.lowercase(),token)
    }

    override fun loginInAccount(login: String, password: String, userAgent: String): LoginResponseModel {
        val accountData = accountDao.getAccountByLogin(login.lowercase())
                ?: throw AccountNotFoundException()

        val passwordHash = secureUtils.getHash(password + accountData.salt)

        return if (passwordHash == accountData.hashPassword) {
            val existToken = tokenDao.findToken(accountData.id, userAgent)
            if (existToken != null) {
                LoginResponseModel(accountData.login,existToken.token)
            } else {
                val newToken = secureUtils.generateToken()
                tokenDao.insertToken(TokenEntity(accountData.id, newToken, userAgent))
                LoginResponseModel(accountData.login,newToken)
            }
        } else {
            throw InvalidPasswordException()
        }
    }

    override fun logout(token: String,userAgent: String) {
        val tokenInfo = tokenDao.findToken(token, userAgent)
            ?: throw SessionExpressionException()

        tokenDao.removeToken(tokenInfo.accountId,tokenInfo.userAgent)
    }

    override fun isValidSession(token: String, userAgent: String) : Boolean {
        return tokenDao.findToken(token, userAgent) != null
    }

}