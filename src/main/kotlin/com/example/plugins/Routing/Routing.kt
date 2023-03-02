package com.example.plugins.Routing

import com.example.domain.AuthManager.AuthManager
import com.example.domain.NoteRepository.NoteRepository
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val authManager by inject<AuthManager>()
    val noteRepository by inject<NoteRepository>()

    configureAuthRouting(authManager)
    configureNoteRouting(noteRepository)
    routing {
        get("/") {
            call.respond(mapOf("isServerStarted" to true))
        }
    }
}
