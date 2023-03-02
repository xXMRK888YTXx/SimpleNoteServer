package com.example.plugins

import com.example.DatabaseFactory
import io.ktor.server.application.*

fun Application.configureDatabase() {
    val url = environment.config.property("DB_URL").getString()
    val user = environment.config.property("DB_USER").getString()
    val password = environment.config.property("DB_PASSWORD").getString()

    DatabaseFactory.create(url,user,password)
}