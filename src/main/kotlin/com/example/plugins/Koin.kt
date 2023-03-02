package com.example.plugins

import com.example.DI.coreModule
import io.ktor.server.application.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(coreModule)
    }
}