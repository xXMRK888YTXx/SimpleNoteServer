package com.example.Database.tables

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text
import org.ktorm.schema.varchar

object NoteTable : Table<Nothing>("Notes") {

    val noteId = int("noteId").primaryKey()

    val accountOwnerId = int("accountOwnerId")

    val noteTitle = varchar("noteTitle")

    val noteText = text("noteText")
}