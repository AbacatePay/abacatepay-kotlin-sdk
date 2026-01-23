package com.abacatepay.client

import com.abacatepay.internal.Constants
import io.ktor.client.plugins.logging.LogLevel

/**
 * Configuration options for the AbacatePay client.
 *
 * @property logLevel Ktor client logging level.
 * @property timeout The timeout duration in milliseconds for HTTP requests.
 * @property apiVersion The API version to be used when interacting with the AbacatePay service.
 * This property must be set to "v1", as only version 1 is currently supported.
 *
 * @throws IllegalArgumentException If the API version is not "v1".
 *
 * @constructor Creates an instance of `AbacatePayOptions` with the specified logging, timeout, and API version settings.
 */
data class AbacatePayOptions(
    val logLevel: LogLevel = LogLevel.NONE,
    val timeout: Long = 15000L,
    val apiVersion: String = "v1"
) {


    init {
        require(apiVersion ==  "v1"){
            "Core only supports v1 API version for now"
        }
    }

    /**
     * The base URL used for making API requests to the AbacatePay service.
     * It is dynamically constructed by combining the host defined in `Constants.ABACATEPAY_API_HOST`
     * and the API version specified in the `apiVersion` property of `AbacatePayOptions`.
     * This property ensures the client interacts with the appropriate version of the API.
     */
    val baseUrl: String = "${Constants.ABACATEPAY_API_HOST}/$apiVersion"
}
