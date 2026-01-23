package com.abacatepay

import com.abacatepay.client.AbacatePayClient
import com.abacatepay.client.AbacatePayOptions
import io.ktor.client.plugins.logging.LogLevel

suspend fun main() {
    val abacatePayClient = AbacatePayClient(
        apiKey = "abc_dev_CAL0sZM21NHtcJ1GjCxcUmTB",
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