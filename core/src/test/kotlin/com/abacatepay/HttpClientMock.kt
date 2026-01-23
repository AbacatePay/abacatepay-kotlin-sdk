package com.abacatepay

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

inline fun <reified T> mockEngine(
    response: T,
    httpStatusCode: HttpStatusCode,
    contentType: String?,
) = MockEngine { _ ->
    respond(
        content = if (response is String) response else Json.encodeToString(response),
        status = httpStatusCode,
        headers = headersOf(
            HttpHeaders.ContentType,
            contentType ?: if (httpStatusCode.isSuccess()) "application/json" else "*/*"
        )
    )
}

fun httpClient(mockEngine: MockEngine) = HttpClient(mockEngine){
    install(ContentNegotiation){
        json()
    }
}