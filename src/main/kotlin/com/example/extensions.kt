package com.example

import com.example.domain.RespondModels.ErrorResponseModel
import com.example.exceptions.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

fun PipelineContext<Unit,ApplicationCall>.getPathParam(key:String) : String {
    return call.parameters[key] ?: throw MissingPathParamException()
}

fun PipelineContext<Unit,ApplicationCall>.getPathParamOrNull(key:String) : String? {
    return try {
        getPathParam(key)
    }catch (e:MissingPathParamException) {
        return null
    }
}

inline fun <reified T> PipelineContext<Unit,ApplicationCall>.getPathParamAndMap(
    key:String,
    onMapParam:(String) -> T
) : T {
    try {
        return onMapParam(getPathParam(key))
    }catch (e:MissingPathParamException) {
        throw e
    } catch (e:Exception) {
        throw InvalidPathParamTypeException()
    }
}

inline fun <reified T> PipelineContext<Unit,ApplicationCall>.getPathParamAndMapOrNull(
    key:String,
    onMapParam:(String) -> T
) : T? {
    return try {
        onMapParam(getPathParam(key))
    }catch (e:Exception) {
        null
    }
}

fun PipelineContext<Unit,ApplicationCall>.getHeaderParam(key:String) : String {
    return call.request.headers[key] ?: throw MissingHeaderParamException()
}

val PipelineContext<Unit,ApplicationCall>.userAgent : String
    get() = call.request.userAgent() ?: throw MissingUserAgentException()


val PipelineContext<Unit,ApplicationCall>.token : String
    get() {
        try {
            return getHeaderParam("token")
        }catch (e:Exception) {
            throw MissingTokenException()
        }
    }


suspend inline fun PipelineContext<Unit,ApplicationCall>.wrapRequest(request:PipelineContext<Unit,ApplicationCall>.() -> Unit) {
    try {
        request()
    }catch (e:ServerException) {
        call.respond(HttpStatusCode.BadRequest,ErrorResponseModel(e))
    }
}