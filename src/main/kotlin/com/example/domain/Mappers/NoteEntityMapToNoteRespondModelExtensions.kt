package com.example.domain.Mappers

import com.example.Database.entity.NoteEntity
import com.example.domain.RespondModels.NoteRespondModel

fun NoteEntity.toNoteRespondModel() : NoteRespondModel {
    return NoteRespondModel(this.noteId,this.noteTitle,this.noteText)
}