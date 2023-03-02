package com.example.Database.tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object TokenTable : Table<Nothing>("Tokens") {

    val accountId = int("accountId")

    val token = varchar("token")

    val userAgent = varchar("userAgent")
}