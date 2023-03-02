package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import com.example.plugins.Routing.configureRouting

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureDatabase()
    configureKoin()
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
