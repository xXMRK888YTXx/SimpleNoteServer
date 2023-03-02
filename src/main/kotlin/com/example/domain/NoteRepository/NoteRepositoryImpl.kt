package com.example.domain.NoteRepository

import com.example.Database.Dao.NoteDao.NoteDao
import com.example.Database.entity.NoteEntity
import com.example.domain.AccountInfoProvider.AccountInfoProvider
import com.example.domain.AuthManager.AuthManager
import com.example.domain.Mappers.toNoteRespondModel
import com.example.domain.RespondModels.NoteRespondModel

class NoteRepositoryImpl(
    private val accountInfoProvider: AccountInfoProvider,
    private val noteDao: NoteDao
) : NoteRepository {
    override fun addNote(token: String, userAgent: String, noteTitle: String, noteText: String) {
        val accountData = accountInfoProvider.getAccountDataByToken(token, userAgent)

        noteDao.insertNote(
            NoteEntity(
                accountOwnerId = accountData.id,
                noteTitle = noteTitle,
                noteText = noteText
            )
        )
    }

    override fun getAllAccountNotes(token: String, userAgent: String): List<NoteRespondModel> {
        val accountData = accountInfoProvider.getAccountDataByToken(token, userAgent)

        return noteDao.getAllAccountNotes(accountData.id).map { it.toNoteRespondModel() }
    }

    override fun removeNote(token: String, userAgent: String, noteId: Int) {
        val accountData = accountInfoProvider.getAccountDataByToken(token, userAgent)

        noteDao.removeNote(accountData.id,noteId)
    }

    override fun updateNote(
        token: String,
        userAgent: String,
        noteId: Int,
        updatedTitle: String?,
        updatedText: String?
    ) {
        val accountData = accountInfoProvider.getAccountDataByToken(token, userAgent)

        noteDao.updateNote(accountData.id,noteId, updatedTitle, updatedText)
    }

}