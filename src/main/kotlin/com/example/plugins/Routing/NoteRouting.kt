package com.example.plugins.Routing

import com.example.*
import com.example.domain.AuthManager.AuthManager
import com.example.domain.NoteRepository.NoteRepository
import com.example.exceptions.MissingPathParamException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureNoteRouting(noteRepository: NoteRepository) {
    val baseUrl = "/notes"

    routing {
        post("$baseUrl/newNote") {
            wrapRequest {
                val noteTitle = getPathParam("noteTitle")
                val noteText = getPathParam("noteText")


                noteRepository.addNote(
                    token = token,
                    userAgent = userAgent,
                    noteTitle = noteTitle,
                    noteText = noteText
                )

                call.respond(HttpStatusCode.OK)
            }
        }

        get("$baseUrl/getNotes") {
           wrapRequest {
               call.respond(
                   HttpStatusCode.OK,
                   noteRepository.getAllAccountNotes(token,userAgent)
               )
           }
        }

        delete("$baseUrl/deleteNote") {
            wrapRequest {
                val noteId = getPathParamAndMap("noteId") {
                    it.toInt()
                }

                call.respond(
                    HttpStatusCode.OK,
                    noteRepository.removeNote(token,userAgent,noteId)
                )
            }
        }

        put("$baseUrl/updateNote") {
            wrapRequest {
                val noteId = getPathParamAndMap("noteId") {
                    it.toInt()
                }
                val updatedNoteTitle = getPathParamOrNull("noteTitle")
                val updateNoteText = getPathParamOrNull("noteText")

                if(updatedNoteTitle == null&&updateNoteText == null)
                    throw MissingPathParamException()

                noteRepository.updateNote(
                    token = token,
                    userAgent = userAgent,
                    noteId = noteId,
                    updatedTitle = updatedNoteTitle,
                    updatedText = updateNoteText,
                )
                call.respond(HttpStatusCode.OK)
            }
        }


    }
}