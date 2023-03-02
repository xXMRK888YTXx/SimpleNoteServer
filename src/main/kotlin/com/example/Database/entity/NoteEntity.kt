package com.example.Database.entity

data class NoteEntity(
    val noteId:Int = 0,
    val accountOwnerId:Int,
    val noteTitle:String,
    val noteText:String
)