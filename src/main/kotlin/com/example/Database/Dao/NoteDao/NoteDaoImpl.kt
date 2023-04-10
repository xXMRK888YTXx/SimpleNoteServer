package com.example.Database.Dao.NoteDao

import com.example.Database.entity.NoteEntity
import com.example.Database.tables.NoteTable
import com.example.Database.tables.TokenTable
import org.ktorm.database.Database
import org.ktorm.database.asIterable
import org.ktorm.dsl.*
import javax.management.Query.and

class NoteDaoImpl(private val database: Database) : NoteDao {

    override fun insertNote(noteEntity: NoteEntity) : NoteEntity {
        database.insert(NoteTable) {
            set(it.noteId,noteEntity.noteId)
            set(it.accountOwnerId, noteEntity.accountOwnerId)
            set(it.noteTitle, noteEntity.noteTitle)
            set(it.noteText, noteEntity.noteText)
        }

        return database.from(NoteTable).select()
            .where { NoteTable.accountOwnerId eq noteEntity.accountOwnerId }
            .orderBy(NoteTable.noteId.desc())
            .limit(1)
            .map {
                NoteEntity(
                    noteId = it[NoteTable.noteId]!!,
                    accountOwnerId = it[NoteTable.accountOwnerId]!!,
                    noteTitle = it[NoteTable.noteTitle]!!,
                    noteText = it[NoteTable.noteText]!!
                )
            }.first()

    }

    override fun getAllAccountNotes(accountId: Int): List<NoteEntity> {
        return database.from(NoteTable).select().where {
            NoteTable.accountOwnerId eq accountId
        }.rowSet.asIterable().map {
            NoteEntity(
                noteId = it[NoteTable.noteId]!!,
                accountOwnerId = it[NoteTable.accountOwnerId]!!,
                noteTitle = it[NoteTable.noteTitle]!!,
                noteText = it[NoteTable.noteText]!!
            )
        }
    }

    override fun removeNote(accountId: Int, noteId: Int) {
        database.delete(NoteTable) {
            it.accountOwnerId eq accountId and(it.noteId eq noteId)
        }
    }

    override fun updateNote(accountId: Int, noteId: Int, updatedTitle: String?, updatedText: String?) {
        database.update(NoteTable) {
            if(updatedTitle != null) {
                set(it.noteTitle,updatedTitle)
            }

            if(updatedText != null) {
                set(it.noteText,updatedText)
            }

            where {
                it.accountOwnerId eq accountId and(it.noteId eq noteId)
            }
        }
    }


}