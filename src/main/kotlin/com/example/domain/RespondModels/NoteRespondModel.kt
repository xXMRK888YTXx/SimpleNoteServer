package com.example.domain.RespondModels

import kotlinx.serialization.Serializable

@Serializable
data class NoteRespondModel (
    val noteId:Int,
    val noteTitle:String,
    val noteText:String
)