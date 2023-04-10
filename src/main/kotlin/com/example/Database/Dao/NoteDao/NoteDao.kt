package com.example.Database.Dao.NoteDao

import com.example.Database.entity.NoteEntity

interface NoteDao {
    fun insertNote(noteEntity: NoteEntity) : NoteEntity

    fun getAllAccountNotes(accountId:Int) : List<NoteEntity>

    fun removeNote(accountId: Int,noteId:Int)

    fun updateNote(accountId: Int,noteId: Int,updatedTitle:String?,updatedText:String?)
}