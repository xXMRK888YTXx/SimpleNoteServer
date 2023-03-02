package com.example.plugins.Routing

import com.example.domain.AuthManager.AuthManager
import com.example.getPathParam
import com.example.token
import com.example.userAgent
import com.example.wrapRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureAuthRouting(authManager: AuthManager) {
    val baseurl = "/auth"

    routing {
        post("$baseurl/register") {
            wrapRequest {
                val login = getPathParam("login")
                val password = getPathParam("password")
                val userAgent = userAgent

                call.respond(HttpStatusCode.OK,authManager.registerAccount(login, password, userAgent))
            }
        }

        post("$baseurl/login") {
            wrapRequest {
                val login = getPathParam("login")
                val password = getPathParam("password")
                val userAgent = userAgent

                call.respond(HttpStatusCode.OK,authManager.loginInAccount(login, password, userAgent))
            }
        }

        post("$baseurl/logout") {
            wrapRequest {
                authManager.logout(token,userAgent)

                call.respond(HttpStatusCode.OK)
            }
        }
    }
}