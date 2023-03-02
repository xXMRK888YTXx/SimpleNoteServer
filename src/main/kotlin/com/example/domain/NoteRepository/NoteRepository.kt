package com.example.domain.NoteRepository

import com.example.Database.entity.NoteEntity
import com.example.domain.RespondModels.NoteRespondModel

interface NoteRepository {
    fun addNote(token:String,userAgent:String,noteTitle:String,noteText:String)

    fun getAllAccountNotes(token: String,userAgent:String) : List<NoteRespondModel>

    fun removeNote(token: String,userAgent:String,noteId:Int)

    fun updateNote(
        token: String,
        userAgent:String,
        noteId: Int,
        updatedTitle:String?,
        updatedText:String?
    )
}