package com.example.Database.tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object AccountTable : Table<Nothing>("Accounts") {

    val id = int("id").primaryKey()

    val login = varchar("login")

    val hashPassword = varchar("hashPassword")

    val salt = varchar("salt")
}