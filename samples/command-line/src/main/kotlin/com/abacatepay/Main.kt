package com.abacatepay

import com.abacatepay.client.AbacatePayClient
import com.abacatepay.client.AbacatePayOptions
import io.ktor.client.plugins.logging.LogLevel

const val API_KEY = "API-KEY"

suspend fun main() {
    val abacatePayClient = AbacatePayClient(
        apiKey = API_KEY,
        options = AbacatePayOptions(
            logLevel = LogLevel.INFO
        ),
    )
    val response = abacatePayClient.billing.list()
    response.fold(
        onSuccess = { println("success: $it") },
        onFailure = { println("failure: $it") }
    )
}