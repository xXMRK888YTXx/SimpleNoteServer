package com.example.Database.Dao.TokenDao

import com.example.Database.tables.TokenTable
import com.example.Database.entity.TokenEntity
import org.ktorm.database.Database
import org.ktorm.database.asIterable
import org.ktorm.dsl.*

class TokenDaoImpl(
    private val database: Database
) : TokenDao {
    override fun insertToken(tokenEntity: TokenEntity) {
        database.insert(TokenTable) {
            set(TokenTable.accountId,tokenEntity.accountId)
            set(TokenTable.token,tokenEntity.token)
            set(TokenTable.userAgent,tokenEntity.userAgent)
        }
    }


    override fun findToken(id: Int, userAgent: String): TokenEntity? {
         return database.from(TokenTable).select().where {
            TokenTable.accountId eq id and(TokenTable.userAgent eq userAgent)
        }.rowSet.asIterable().map {
            TokenEntity(
                it[TokenTable.accountId]!!,
                it[TokenTable.token]!!,
                it[TokenTable.userAgent]!!
            )
         }.firstOrNull()
    }

    override fun findToken(token: String, userAgent: String): TokenEntity? {
        return database.from(TokenTable).select().where {
            TokenTable.token eq token and(TokenTable.userAgent eq userAgent)
        }.rowSet.asIterable().map {
            TokenEntity(
                it[TokenTable.accountId]!!,
                it[TokenTable.token]!!,
                it[TokenTable.userAgent]!!
            )
        }.firstOrNull()
    }

    override fun removeToken(id: Int, userAgent: String) {
        database.delete(TokenTable) {
            it.accountId eq id and(it.userAgent eq userAgent)
        }
    }
}