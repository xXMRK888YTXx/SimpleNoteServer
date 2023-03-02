package com.example.Database.Dao.AccountDao

import com.example.Database.tables.AccountTable
import com.example.Database.entity.AccountEntity
import org.ktorm.database.Database
import org.ktorm.database.asIterable
import org.ktorm.dsl.*

class AccountDaoImpl(
    private val database: Database
) : AccountDao {
    override fun insertAccount(accountEntity: AccountEntity): Int {
        database.insert(AccountTable) {
            set(AccountTable.id, accountEntity.id)
            set(AccountTable.login, accountEntity.login)
            set(AccountTable.hashPassword, accountEntity.hashPassword)
            set(AccountTable.salt, accountEntity.salt)
        }

        return database.from(AccountTable).select(AccountTable.id).where {
            AccountTable.login eq (accountEntity.login)
        }.rowSet.asIterable().map { it[AccountTable.id] }.firstNotNullOf { it }
    }

    override fun getAccountByLogin(login: String): AccountEntity? {
        return database.from(AccountTable).select().where {
            AccountTable.login eq login
        }.rowSet.asIterable().map {
            AccountEntity(
                id = it[AccountTable.id]!!,
                login = it[AccountTable.login]!!,
                hashPassword = it[AccountTable.hashPassword]!!,
                salt = it[AccountTable.salt]!!
            )
        }.firstOrNull()
    }

    override fun getAccountById(id: Int): AccountEntity? {
        return database.from(AccountTable).select().where {
            AccountTable.id eq id
        }.rowSet.asIterable().map {
            AccountEntity(
                id = it[AccountTable.id]!!,
                login = it[AccountTable.login]!!,
                hashPassword = it[AccountTable.hashPassword]!!,
                salt = it[AccountTable.salt]!!
            )
        }.firstOrNull()
    }

    override fun isAccountExist(id: Int): Boolean {
        return database.from(AccountTable).select(AccountTable.login).where {
            AccountTable.id eq id
        }.rowSet.asIterable().map { it[AccountTable.login] }.firstOrNull() != null
    }

    override fun isAccountExist(login: String): Boolean {
        return database.from(AccountTable).select(AccountTable.id).where {
            AccountTable.login eq login
        }.rowSet.asIterable().map { it[AccountTable.id] }.firstOrNull() != null
    }
}